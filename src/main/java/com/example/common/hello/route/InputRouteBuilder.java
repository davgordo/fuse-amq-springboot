package com.example.common.hello.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class InputRouteBuilder extends RouteBuilder {

    private String out = "amq:queue:hello.message";

    public String getOut() { return out; }

    public void setOut(String out) {
        this.out = out;
    }

    @Override
    public void configure() throws Exception {

        from("direct:input")
                .id("enqueue-messages")
                .convertBodyTo(String.class)
                .log("producing message ${body} to AMQ")
                .to(out)
                .removeHeaders("*")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant("201"))
                .setBody(constant(null));

    }

}
