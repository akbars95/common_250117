package com.mtsmda.word.controller;

import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.word.config.security.LimitLoginAuthenticationProvider;
import com.mtsmda.word.config.security.SecurityConfiguration;
import com.mtsmda.word.service.UserAttemptService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static com.mtsmda.word.controller.PageURL.*;
import static com.mtsmda.word.controller.PageURL.StaticPageURL.LOGOUT_PAGE_URL;

/**
 * Created by dminzat on 2/5/2017.
 */
@Controller
public class StaticPageController {

    private static final Logger LOGGER = Logger.getLogger(StaticPageController.class);

    @Autowired
    private UserAttemptService userAttemptService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping({ROOT, StaticPageURL.INDEX_PAGE_URL})
    public String index(Model model) {
        model.addAttribute("recipient", "Hello");
        LOGGER.info("get index page");
        return StaticPageURL.INDEX_PAGE_IN;
    }

    @GetMapping({StaticPageURL.LOGIN_PAGE_URL})
    public ModelAndView login(HttpServletRequest request, Locale locale,
                              @RequestParam(value = "loginError", required = false) String loginError,
                              @RequestParam(value = "expired", required = false) String expired,
                              @RequestParam(value = "invalid_session", required = false) String invalidSession) {
        ModelAndView modelAndView = new ModelAndView(StaticPageURL.LOGIN_PAGE_IN);

        if (ObjectHelper.objectIsNotNull(loginError)) {
            modelAndView.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION", locale));
        }
        if (ObjectHelper.objectIsNotNull(expired)) {
            modelAndView.addObject("error", messageSource.getMessage("code.application.session.expired", new Object[]{}, locale));
        }
        if (ObjectHelper.objectIsNotNull(invalidSession)) {
            modelAndView.addObject("error", messageSource.getMessage("code.application.session.invalid", new Object[]{}, locale));
        }

        LOGGER.info("get login page");
        return modelAndView;
    }

    /*
    * if use this method cannot use http.logout().logoutUrl(LOGOUT_PAGE_URL), because will be log out but web page not refreshed
    * */
    @GetMapping({LOGOUT_PAGE_URL})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("get logout page");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("username is - " + auth.getName());
        LOGGER.info(auth.toString());
        if (ObjectHelper.objectIsNotNull(auth)) {
            new CookieClearingLogoutHandler("JSESSIONID", SecurityConfiguration.REMEMBER_ME_COOKIE_NAME).logout(request, response, auth);
            new SecurityContextLogoutHandler().logout(request, response, auth);
            System.out.println(auth == null);
        }
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ROOT;
    }

    @GetMapping({ProtectedPageURL.PROTECT_INDEX_PAGE_URL})
    public String indexProtected(Model model) {
        model.addAttribute("recipient", "Hello, this is protected file!");
        LOGGER.info("get protected index page");
        return ProtectedPageURL.PROTECT_INDEX_PAGE_IN;
    }

    @GetMapping({StaticPageURL.ACCESS_DENIED_PAGE_URL})
    public String accessDenied() {
        LOGGER.info("get access denied page");
        return StaticPageURL.ACCESS_DENIED_PAGE_IN;
    }

    @GetMapping({StaticPageURL.REGISTRATION_PAGE_URL})
    public String registration() {
        LOGGER.info("get registration page");
        return StaticPageURL.REGISTRATION_PAGE_IN;
    }

    /*@GetMapping({"/home"})
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.getModelMap().addAttribute("recipient", "Hello");
        modelAndView.setView(new InternalResourceView("/WEB-INF/templates/jsp/home.jsp"));
        LOGGER.info("get home page");
//        return "home";
        return modelAndView;
    }*/

    private String getErrorMessage(HttpServletRequest request, String key, Locale locale) {
        Exception exception = (Exception) request.getSession().getAttribute(key);
        if (exception instanceof BadCredentialsException) {
            return messageSource.getMessage("code.application.bad.credentials", new Object[]{}, locale);
        } else if (exception instanceof LockedException) {
            if (ObjectHelper.objectIsNotNull(exception.getMessage())) {
                if (exception.getMessage().startsWith(LimitLoginAuthenticationProvider.CUSTOM_ERROR)) {
                    String username = exception.getMessage().substring(exception.getMessage().indexOf(LimitLoginAuthenticationProvider.USERNAME_DELIMITER) + 1);//SecurityContextHolder.getContext().getAuthentication().getName();
                    return messageSource.getMessage("code.application.user.locked", new Object[]{}, locale) + "! "
                            + messageSource.getMessage("code.application.username.text", new Object[]{username}, locale)
                            + ". " + messageSource.getMessage("code.application.user.last.attempt",
                            new Object[]{LocalDateTimeHelper.convertLocalDateTimeToString(userAttemptService.getUserAttemptByUsername(username).getLastModified(), LocalDateTimeHelper.NORMAL_DATE_TIME_FORMAT)}, locale);
                } else {
                    return exception.getMessage();
                }
            }
            return exception.getMessage();
        } else {
            return messageSource.getMessage("code.application.bad.credentials", new Object[]{}, locale);
        }
    }

}