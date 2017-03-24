package com.mtsmda.word.config.security;

import com.mtsmda.helper.ExceptionMessageHelper;
import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.model.UserAttempt;
import com.mtsmda.word.nonConfig.common.LoggerI;
import com.mtsmda.word.nonConfig.repository.UserDetailRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by dminzat on 3/3/2017.
 */
@Component("limitLoginAuthenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider implements LoggerI {

    @Autowired
    private UserDetailRepository userDetailRepository;

    public static final String CUSTOM_ERROR = "CUSTOM_ERROR";
    public static final String USERNAME_DELIMITER = "|";

    protected static Logger LOGGER = null;

    @Override
    @PostConstruct
    public <T> void setLogger() {
        if(ObjectHelper.objectIsNull(LOGGER)){
            LOGGER = Logger.getLogger(this.getClass());
        }
    }

    @Autowired
    @Qualifier("customJdbcDaoImplUserDetailsService")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
        setLogger();
        LOGGER.info("set user details");
    }

    @Autowired
    @Qualifier("passwordEncoder")
    @Override
    public void setPasswordEncoder(Object passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
        setLogger();
        LOGGER.info("set password encoder");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        setLogger();
        LOGGER.info("authenticate");
        try {
            Authentication authenticate = super.authenticate(authentication);
            userDetailRepository.resetFailAttempts(authenticate.getName());
            LOGGER.info("success authenticate - " + authenticate.getName());
            return authenticate;
        } catch (BadCredentialsException e) {
            userDetailRepository.updateFailAttempts(authentication.getName());
            LOGGER.warn("exception authenticate - " + authentication.getName() + "\texception - "
                    + ExceptionMessageHelper.exceptionDescription(e));
            throw e;
        } catch (LockedException e) {
            StringBuilder error = new StringBuilder();
            UserAttempt userAttempt = userDetailRepository.getUserAttempts(authentication.getName());
            if (ObjectHelper.objectIsNotNull(userAttempt)) {
                error.append(CUSTOM_ERROR).append(USERNAME_DELIMITER).append(authentication.getName());
            } else {
                error.append(e.getMessage());
            }
            LOGGER.warn(error);
            LOGGER.warn("exception authenticate - " + authentication.getName() + "\texception - "
                    + ExceptionMessageHelper.exceptionDescription(e));
            throw new LockedException(error.toString());
        }
        catch (AccountStatusException e){
            LOGGER.info(e.getMessage());
            throw e;
        }
    }
}