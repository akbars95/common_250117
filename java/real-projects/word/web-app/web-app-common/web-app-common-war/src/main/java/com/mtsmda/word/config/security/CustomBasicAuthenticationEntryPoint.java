package com.mtsmda.word.config.security;

import com.mtsmda.word.nonConfig.common.LoggerI;
import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dminzat on 2/20/2017.
 */
@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint implements LoggerI {

    protected static Logger LOGGER = null;
    public static final String REALM_NAME = "W_REALM_NAME";

    /*public CustomBasicAuthenticationEntryPoint() {
        setLogger();
    }*/

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
        LOGGER.info("add header\tWWW-Authenticate" + "\tBasic realm=" + getRealmName() + "");
        String temp = "HTTP Status 401: " + authException.getMessage();
        response.getWriter().print(temp);
        LOGGER.info(temp);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(REALM_NAME);
        super.afterPropertiesSet();
    }

    @Override
    @PostConstruct
    public <T> void setLogger() {
        LOGGER = Logger.getLogger(this.getClass());
    }
}