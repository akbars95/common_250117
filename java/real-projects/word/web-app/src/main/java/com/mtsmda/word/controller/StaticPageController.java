package com.mtsmda.word.controller;

import com.mtsmda.word.service.LanguageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mtsmda.word.controller.PageURL.*;

/**
 * Created by dminzat on 2/5/2017.
 */
@Controller
public class StaticPageController {

    private static final Logger LOGGER = Logger.getLogger(StaticPageController.class);

    @Autowired
    private LanguageService languageService;

    @GetMapping({ROOT, StaticPageURL.INDEX_PAGE_URL})
    public String index(Model model) {
        model.addAttribute("recipient", "Hello");
        LOGGER.info("get index page");
        languageService.getAllLanguages().forEach(language -> {
            System.out.println(language.toString());
        });
        return StaticPageURL.INDEX_PAGE_IN;
    }

    @GetMapping({StaticPageURL.LOGIN_PAGE_URL})
    public String login() {
        LOGGER.info("get login page");
        return StaticPageURL.LOGIN_PAGE_IN;
    }

    @GetMapping({StaticPageURL.LOGOUT_PAGE_URL})
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("get logout page");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("username is - " + auth.getName());
        LOGGER.info(auth.toString());
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return UrlBasedViewResolver.REDIRECT_URL_PREFIX + ROOT;
    }

    @GetMapping({ProtectedPageURL.PROTECT_INDEX_PAGE_URL})
    public String indexProtected(Model model) {
        model.addAttribute("recipient", "Hello, this is protected file!");
        LOGGER.info("get protected index page");
        languageService.getAllLanguages().forEach(language -> {
            System.out.println(language.toString());
        });
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

}