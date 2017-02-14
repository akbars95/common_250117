package com.mtsmda.apache.camel.withoutspring.route;

import com.mtsmda.apache.camel.withoutspring.bean.StringBean;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by dminzat on 2/14/2017.
 */
public class StringRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("direct:string").routeId("string-route").bean(StringBean.class, "translatorUpper").log("Upper - ${body}")
                .bean(StringBean.class, "translatorLower").log("Lower - ${body}");
    }
}