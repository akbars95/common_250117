package com.mtsmda.word.test.config;

import com.mtsmda.word.config.other.DatabaseConfiguration;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by dminzat on 3/14/2017.
 */
@EnableTransactionManagement
@TestPropertySource("classpath:database.properties")
@ComponentScan(basePackages = {"com.mtsmda.word.nonConfig"})
@Import(value = DatabaseConfiguration.class)
public class TestApplicationContext {

}