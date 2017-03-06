package com.mtsmda.word.config;

import com.mtsmda.word.config.security.SecurityConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Properties;

/**
 * Created by dminzat on 2/5/2017.
 */
@Configuration
@ComponentScan("com.mtsmda.word")
@EnableWebMvc
@PropertySource("classpath:spring/properties/database.properties")
@Import({ThymeleafConfiguration.class, DatabaseConfiguration.class, SecurityConfiguration.class})
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter {

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLocaleChangeInterceptor());
    }

    //*******************************************************************************************************

    /*@Bean
    @Description("view resolver for jsp")
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/templates/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setViewNames("*.jsp");
//        internalResourceViewResolver.setOrder(99);
        return internalResourceViewResolver;
    }*/

    @Bean("commonsMultipartResolver")
    @Description("when send post multipart forms")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Bean("messageSource")
    @Description("internationalization")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setBasename("classpath:/i18n/word_messages");
        return reloadableResourceBundleMessageSource;
    }

    @Bean("localeChangeInterceptor")
    public LocaleChangeInterceptor getLocaleChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Bean("localeResolver")
    public SessionLocaleResolver getSessionLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("en"));
        return sessionLocaleResolver;
    }

    /*@Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("en"));
        cookieLocaleResolver.setCookieName("localeCookie");
        cookieLocaleResolver.setCookieMaxAge(5 * 60);
        return cookieLocaleResolver;
    }*/

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver
    createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r =
                new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("DatabaseException", "404");
        mappings.setProperty("InvalidCreditCardException", "404");

        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("404");    // No default
        r.setExceptionAttribute("ex");     // Default is "exception"
        r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }

}