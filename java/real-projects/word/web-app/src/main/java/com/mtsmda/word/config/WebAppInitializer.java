package com.mtsmda.word.config;

import com.mtsmda.word.listener.web.SessionHTTPListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by dminzat on 2/6/2017.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootApplicationContext = new AnnotationConfigWebApplicationContext();
//        rootApplicationContext.register();

        servletContext.addListener(new ContextLoaderListener(rootApplicationContext));
        servletContext.addListener(new SessionHTTPListener());

        AnnotationConfigWebApplicationContext dispatcherServletApplicationContext = new AnnotationConfigWebApplicationContext();
        dispatcherServletApplicationContext.register(SpringMVCConfig.class);
        dispatcherServletApplicationContext.setServletContext(servletContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherServletApplicationContext));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }
}