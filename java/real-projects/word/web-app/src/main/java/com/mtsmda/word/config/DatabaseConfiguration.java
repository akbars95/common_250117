package com.mtsmda.word.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dminzat on 2/14/2017.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;
    
    @Bean
    public DriverManagerDataSource driverManagerDataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("oracle.local.driver.name"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("oracle.local.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("oracle.local.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("oracle.local.password"));
        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(driverManagerDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(driverManagerDataSource());
    }

}