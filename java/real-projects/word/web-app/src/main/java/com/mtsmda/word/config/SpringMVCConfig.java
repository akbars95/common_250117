package com.mtsmda.word.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * Created by dminzat on 2/5/2017.
 */
@Configuration
@ComponentScan("com.mtsmda.word")
@EnableWebMvc
@PropertySource("classpath:spring/properties/database.properties")
public class SpringMVCConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean("internalResourceViewResolver")
    @Description("view resolver for jsp")
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/templates/jsp/", ".jsp");
    }

    @Bean("commonsMultipartResolver")
    @Description("when send post multipart forms")
    public CommonsMultipartResolver getCommonsMultipartResolver(){
        return new CommonsMultipartResolver();
    }

    @Bean("thymeleafViewResolver")
    @Description("add support view resolver")
    public ThymeleafViewResolver getThymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(getSpringTemplateEngine());
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    @Bean("springTemplateEngine")
    @Description("settings spring template engine")
    public SpringTemplateEngine getSpringTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(getITemplateResolver());
        springTemplateEngine.setEnableSpringELCompiler(true);
        return springTemplateEngine;
    }

    @Bean(name = "servletContextTemplateResolver")
    @Description("add thymeleaf view resolver")
    public ITemplateResolver getITemplateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setApplicationContext(applicationContext);
        springResourceTemplateResolver.setPrefix("/WEB-INF/templates/thymeleaf/");
        springResourceTemplateResolver.setSuffix(".html");
        springResourceTemplateResolver.setTemplateMode(TemplateMode.HTML);
        return springResourceTemplateResolver;
    }

    @Bean("reloadableResourceBundleMessageSource")
    @Description("internationalization")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setBasename("classpath:i18n/word_messages");
        return reloadableResourceBundleMessageSource;
    }

    /*@Bean(name = "servletContextTemplateResolver")
    public ServletContextTemplateResolver getServletContextTemplateResolver() {
        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver();
        servletContextTemplateResolver.setPrefix("/WEB-INF/templates/");
        servletContextTemplateResolver.setSuffix(".html");
        servletContextTemplateResolver.setTemplateMode("HTML5");
        return servletContextTemplateResolver;
    }*/
}