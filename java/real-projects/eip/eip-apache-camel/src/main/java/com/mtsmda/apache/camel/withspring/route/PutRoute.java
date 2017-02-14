package com.mtsmda.apache.camel.withspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by dminzat on 2/14/2017.
 */
@Component
public class PutRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("timer://init?period=1500").routeId("init-route").process(exchange -> {
            exchange.getIn().setBody("now time is - " + LocalDateTime.now());
        }).to("direct:f1");
    }

}