package com.mtsmda.word.config.web;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by dminzat on 3/13/2017.
 */
//@Deprecated
public class CustomAbstractAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[]{SpringMVCConfiguration.class};
        return new Class<?>[]{SpringMVCConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
//        return new Class<?>[]{SpringMVCConfiguration.class};
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}