package com.mtsmda.apache.camel.withoutspring.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dminzat on 2/14/2017.
 */
public class TimerRoute extends RouteBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimerRoute.class);
    private int index = -1;

    @Override
    public void configure() throws Exception {
        List<String> collect = Stream.of("Barcelona", "Milan", "Spartak", "Chelsea", "Real").collect(Collectors.toList());
        from("timer://timerFirst?period=1000").routeId("timer-route").process(exchange -> {
            Object body = exchange.getIn().getBody();
            if (null == body) {
                exchange.getIn().setBody(LocalDateTime.now());
            }
            System.out.println("process - " + body);
        }).log(LoggingLevel.INFO, "logger - ${body}");

        from("timer://timer2?period=5000").routeId("timer2-route").process(exchange -> {
            String message = null;
            if (++index > collect.size() - 1) {
                message = "Collection is empty!";
            } else {
                message = collect.get(index);
            }
            exchange.getIn().setBody(message);
        }).multicast().to("direct:string", "direct:register");
    }

}