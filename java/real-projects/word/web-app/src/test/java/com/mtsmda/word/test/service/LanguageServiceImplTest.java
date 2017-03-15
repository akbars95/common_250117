package com.mtsmda.word.test.service;

import com.mtsmda.word.nonConfig.service.LanguageService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import static org.testng.Assert.assertNotNull;

/**
 * Created by dminzat on 2/28/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@ComponentScan(basePackages = "com.mtsmda.word.nonConfig")
public class LanguageServiceImplTest {

    @Autowired
    private LanguageService languageService;

    @Test
    @Ignore
    public void test() {
        assertNotNull(languageService);
    }

}