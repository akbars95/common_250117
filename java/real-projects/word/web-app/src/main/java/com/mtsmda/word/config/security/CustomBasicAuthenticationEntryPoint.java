package com.mtsmda.word.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dminzat on 2/20/2017.
 */
@Component
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    public static final String REALM_NAME = "W_REALM_NAME";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");

        response.getWriter().print("HTTP Status 401: " + authException.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName(REALM_NAME);
        super.afterPropertiesSet();
    }
}