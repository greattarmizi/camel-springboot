/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.tarmizi.camel.springboot.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author Tarmizi<tarmizi.id>
 * Jul 26, 2020 1:09:40 PM
 */
@Component
public class SimpleCamelRoute extends RouteBuilder{
    
    @Autowired
    Environment environment;
    
    @Override
    public void configure() throws Exception {
        
        CamelContext context = new DefaultCamelContext();
        context.setStreamCaching(Boolean.TRUE);
        
        from("{{startRoute}}")
                .log("Timer Invoked and the body ${body}" + environment.getProperty("message"))
                .pollEnrich("{{fromRoute}}")
                .streamCaching()
                .to("{{toRoute1}}");
    }
    
}
