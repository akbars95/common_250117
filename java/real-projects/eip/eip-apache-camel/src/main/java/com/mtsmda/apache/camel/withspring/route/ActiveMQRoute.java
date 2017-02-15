package com.mtsmda.apache.camel.withspring.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by dminzat on 2/14/2017.
 */
@Component
public class ActiveMQRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:first?clientId=FirstPetr").log("${body}");

        from("activemq:queue:firstQ?clientId=firstQ").log("${body}").to("activemq:queue:secondQ?clientId=secondQ");

    }

}