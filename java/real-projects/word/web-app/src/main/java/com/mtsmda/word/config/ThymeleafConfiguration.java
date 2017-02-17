package com.mtsmda.word.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.Ordered;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by dminzat on 2/10/2017.
 */
@Configuration
public class ThymeleafConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    @Description("add thymeleaf view resolver")
    public SpringResourceTemplateResolver getITemplateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setPrefix("/WEB-INF/templates/thymeleaf/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
//        springResourceTemplateResolver.setCacheable(true);
        return springResourceTemplateResolver;
    }

    @Bean
    @Description("settings spring template engine")
    public SpringTemplateEngine getSpringTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(getITemplateResolver());
        springTemplateEngine.setEnableSpringELCompiler(true);
        springTemplateEngine.addDialect(new SpringSecurityDialect());
        return springTemplateEngine;
    }

    @Bean
    @Description("add support view resolver")
    public ThymeleafViewResolver getThymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(getSpringTemplateEngine());
        thymeleafViewResolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        thymeleafViewResolver.setViewNames(new String[]{".html", ".xhtml", ".htm"});
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setContentType("text/html; charset=utf-8");
        return thymeleafViewResolver;
    }

}