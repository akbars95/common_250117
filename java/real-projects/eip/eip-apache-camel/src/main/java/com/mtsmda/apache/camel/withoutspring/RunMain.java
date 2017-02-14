package com.mtsmda.apache.camel.withoutspring;

import com.mtsmda.apache.camel.withoutspring.bean.RegisterBean;
import com.mtsmda.apache.camel.withoutspring.bean.SimpleBean;
import com.mtsmda.apache.camel.withoutspring.route.StringRoute;
import com.mtsmda.apache.camel.withoutspring.route.TimerRoute;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.spi.Registry;
import org.apache.camel.util.jndi.JndiContext;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by dminzat on 2/14/2017.
 */
public class RunMain {

    public static void main(String[] args) {
        /*LocalDateTime localDateTimeMinus1 = LocalDateTime.now().minusMinutes(1).minusHours(1);
        System.out.println(localDateTimeMinus1);
        Duration between = Duration.between(localDateTimeMinus1, LocalDateTime.now());
        System.out.println(between);
        System.out.println(between.getSeconds());
        System.out.println(between.getSeconds());
        *//*
        System.out.println(between.getNano());
        System.out.println((between.getNano() / 1_000_000_000));*//*
        System.out.println(between.getUnits());*/
        try {
            run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static final void run() throws Exception {
        JndiContext jndiContext = new JndiContext();
        jndiContext.bind("registerBean", new RegisterBean());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addRoutes(new TimerRoute());
        camelContext.addRoutes(new StringRoute());
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:in").routeId("simple-bean").bean(SimpleBean.class).log("simple body - ${body}");
            }
        });
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:register").routeId("register-route").to("bean:registerBean");
            }
        });

        camelContext.start();

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:in", "Car");

        Thread.sleep(1_000 * 360);
        camelContext.stop();
    }

}