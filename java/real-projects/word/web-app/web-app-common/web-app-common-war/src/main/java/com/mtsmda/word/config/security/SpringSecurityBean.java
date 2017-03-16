package com.mtsmda.word.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import static com.mtsmda.word.config.security.SpringSecurityConfiguration.REMEMBER_ME_COOKIE_NAME;
import static com.mtsmda.word.config.security.SpringSecurityConfiguration.REMEMBER_ME_KEY_NAME;

/**
 * Created by dminzat on 3/16/2017.
 */
@Configuration
public class SpringSecurityBean {

//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        return new CustomJdbcTokenRepositoryImpl();
//    }

    @Bean
    public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
        TokenBasedRememberMeServices tokenBasedRememberMeServices = new TokenBasedRememberMeServices(REMEMBER_ME_KEY_NAME, customJdbcDao());
        tokenBasedRememberMeServices.setTokenValiditySeconds(60 * 60 * 24);
        tokenBasedRememberMeServices.setParameter("w_remember_me");
        tokenBasedRememberMeServices.setCookieName(REMEMBER_ME_COOKIE_NAME);
//        tokenBasedRememberMeServices.setUseSecureCookie(true);
        return tokenBasedRememberMeServices;
    }

    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider(REMEMBER_ME_KEY_NAME);
    }

    @Bean
    public CustomJdbcDaoImplUserDetailsService customJdbcDao() {
        return new CustomJdbcDaoImplUserDetailsService();
    }

    @Bean
    public CustomBasicAuthenticationEntryPoint getCustomBasicAuthenticationEntryPoint() {
        return new CustomBasicAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}