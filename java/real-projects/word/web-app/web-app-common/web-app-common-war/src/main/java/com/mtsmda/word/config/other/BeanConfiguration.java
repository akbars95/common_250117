package com.mtsmda.word.config.other;

import com.mtsmda.spring.helper.service.MailService;
import com.mtsmda.spring.helper.service.MailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by dminzat on 3/13/2017.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public MailService getMailService(){
        return new MailServiceImpl();
    }

}