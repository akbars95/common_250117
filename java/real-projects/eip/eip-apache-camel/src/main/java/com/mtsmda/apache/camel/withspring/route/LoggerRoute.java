package com.mtsmda.apache.camel.withspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by dminzat on 2/14/2017.
 */
@Component
public class LoggerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:f1").routeId("f1-route").log("${body}");
    }
}