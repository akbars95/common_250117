package com.mtsmda.word.test.config;

import com.mtsmda.word.config.other.BeanConfiguration;
import com.mtsmda.word.config.other.DatabaseConfiguration;
import com.mtsmda.word.config.web.SpringMVCConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dminzat on 2/28/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ContextConfiguration(classes = TestApplicationContext.class, loader = AnnotationConfigContextLoader.class)
@TestPropertySource("classpath:spring/properties/database.properties")
@ComponentScan(basePackages = {"com.mtsmda.word"})
//@WebAppConfiguration
@Deprecated
public class TestConfiguration {

}