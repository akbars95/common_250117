package com.mtsmda.word.config.web;

import com.mtsmda.word.config.other.BeanConfiguration;
import com.mtsmda.word.config.other.DatabaseConfiguration;
import com.mtsmda.word.config.security.SpringSecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

/**
 * Created by dminzat on 2/5/2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mtsmda.word")
@Import({ThymeleafConfiguration.class, BeanConfiguration.class, SpringSecurityConfiguration.class, DatabaseConfiguration.class, SpringMVCBean.class})
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter {

//    @Autowired
//    private ServletContext servletContext;

    @Autowired
    private SpringMVCBean springMVCBean;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable(/*"dispatcher"*/);
    }

    /*@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("myCustomDefaultServlet");
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebContentInterceptor webContentInterceptor = new WebContentInterceptor();
        webContentInterceptor.setCacheSeconds(0);
        /*webContentInterceptor.setUseExpiresHeader(true);
        webContentInterceptor.setUseCacheControlHeader(true);
        webContentInterceptor.setUseCacheControlNoStore(true);*/
        registry.addInterceptor(webContentInterceptor);
        registry.addInterceptor(springMVCBean.getLocaleChangeInterceptor());
    }

}