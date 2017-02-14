package com.mtsmda.apache.camel.withspring;

import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by dminzat on 2/14/2017.
 */
@Configuration
@Import(SpringBeanConfiguration.class)
@ComponentScans(@ComponentScan("com.mtsmda.apache.camel.withspring"))
public class CustomCamelConfiguration extends CamelConfiguration {



}