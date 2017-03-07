package com.mtsmda.word.config.security;

import com.mtsmda.helper.LocalDateTimeHelper;
import com.mtsmda.helper.ObjectHelper;
import com.mtsmda.real.project.user.model.UserAttempt;
import com.mtsmda.word.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by dminzat on 3/3/2017.
 */
@Component("limitLoginAuthenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserDetailRepository userDetailRepository;

    public static final String CUSTOM_ERROR = "CUSTOM_ERROR";
    public static final String USERNAME_DELIMITER = "|";

    @Autowired
    @Qualifier("customJdbcDaoImpl")
    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
    }

    @Autowired
    @Qualifier("passwordEncoder")
    @Override
    public void setPasswordEncoder(Object passwordEncoder) {
        super.setPasswordEncoder(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Authentication authenticate = super.authenticate(authentication);
            userDetailRepository.resetFailAttempts(authenticate.getName());
            return authenticate;
        } catch (BadCredentialsException e) {
            userDetailRepository.updateFailAttempts(authentication.getName());
            throw e;
        } catch (LockedException e) {
            StringBuilder error = new StringBuilder();
            UserAttempt userAttempt = userDetailRepository.getUserAttempts(authentication.getName());
            if (ObjectHelper.objectIsNotNull(userAttempt)) {
                error.append(CUSTOM_ERROR).append(USERNAME_DELIMITER).append(authentication.getName());
            } else {
                error.append(e.getMessage());
            }
            throw new LockedException(error.toString());
        }
    }
}