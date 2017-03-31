package com.mtsmda.apache.camel.withoutspring;

import com.mtsmda.apache.camel.withoutspring.bean.ObjectBean;
import com.mtsmda.apache.camel.withoutspring.bean.RegisterBean;
import com.mtsmda.apache.camel.withoutspring.bean.SimpleBean;
import com.mtsmda.apache.camel.withoutspring.model.Car;
import com.mtsmda.apache.camel.withoutspring.route.StringRoute;
import com.mtsmda.apache.camel.withoutspring.route.TimerRoute;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

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
//        org.apache.camel.spring.SpringCamelContext
        DefaultCamelContext Context = new DefaultCamelContext(jndiContext);
        Context.addRoutes(new TimerRoute());
        Context.addRoutes(new StringRoute());
        Context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:in").routeId("simple-bean").bean(SimpleBean.class).log("simple body - ${body}");
            }
        });
        Context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:in2").routeId("simple-bean2")
//                        .log("simple body carMark - ${bodyAs(com.mtsmda.apache.camel.withoutspring.model.Car.class)}")
                        .log("simple body carMark1 - ${body}" + simple("${body.getCarModel}").getText())
                        .log("simple body getCarId - ${body}" + simple("${body.getCarId}").getText())
                        .log("simple body carMark11 - ${body}" + simple("${body}", Car.class) )
                        .bean(ObjectBean.class)
                .log("simple body getCarId333 - ${body}");
            }
        });
        Context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:register").routeId("register-route").to("bean:registerBean");
            }
        });

        Context.start();

        ProducerTemplate producerTemplate = Context.createProducerTemplate();
        producerTemplate.sendBody("direct:in", "Car");
        producerTemplate.sendBody("direct:in2", new Car(1, "Opel", "Ascona"));

        Thread.sleep(1_000 * 360);
        Context.stop();
    }

}