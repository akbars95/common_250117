package com.mtsmda.apache.camel.withspring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by dminzat on 2/14/2017.
 */
public class SpringRunMain {

    public static void main(String[] args) {
        try {
            AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(CustomCamelConfiguration.class);
            annotationConfigApplicationContext.start();
            Thread.sleep(1_000 * 100);
            annotationConfigApplicationContext.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}