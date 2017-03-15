package com.mtsmda.word.config.web;

import com.mtsmda.word.config.other.BeanConfiguration;
import com.mtsmda.word.config.other.DatabaseConfiguration;
import com.mtsmda.word.config.security.SecurityConfiguration;
import com.mtsmda.word.nonConfig.web.SessionHTTPListener;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by dminzat on 2/6/2017.
 */
public class WebAppInitializer /*implements WebApplicationInitializer*/ {

    /*@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootApplicationContext = new AnnotationConfigWebApplicationContext();
        rootApplicationContext.register(SpringMVCConfiguration.class);
        rootApplicationContext.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(rootApplicationContext));
        servletContext.addListener(new SessionHTTPListener());

        servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true)).addMappingForUrlPatterns(null, false, "*//*");

//        DispatcherServlet dispatcherServlet = new DispatcherServlet(rootApplicationContext);
//        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(rootApplicationContext)*//*dispatcherServlet*//*);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");
    }*/
}