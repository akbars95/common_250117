package com.mtsmda.word.config.other;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dminzat on 2/14/2017.
 */
@Configuration
@PropertySource("classpath:spring/properties/database.properties")
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public BasicDataSource driverManagerDataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(environment.getRequiredProperty("oracle.local.driver.name"));
        basicDataSource.setUrl(environment.getRequiredProperty("oracle.local.url"));
        basicDataSource.setUsername(environment.getRequiredProperty("oracle.local.username"));
        basicDataSource.setPassword(environment.getRequiredProperty("oracle.local.password"));
        return basicDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(driverManagerDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(driverManagerDataSource());
    }

    @Bean
    public SimpleJdbcCall simpleJdbcCall() {
        return new SimpleJdbcCall(jdbcTemplate());
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(driverManagerDataSource());
    }

}