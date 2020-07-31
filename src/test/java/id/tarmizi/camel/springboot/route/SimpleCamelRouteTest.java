/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.tarmizi.camel.springboot.route;

import java.io.File;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author Tarmizi<tarmizi.id>
 * Jul 31, 2020 1:58:33 PM
 */
@ActiveProfiles("dev")
@RunWith(CamelSpringBootRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class SimpleCamelRouteTest {
    
    @Autowired
    ProducerTemplate producerTemplate;
    
    @Autowired
    Environment environment;
    
    @Test
    public void testMoveFile() throws InterruptedException{
        
        String message = "type,sku#,itemdescription,price\n" +
                "ADD,100,Samsung TV,500\n" +
                "ADD,101,LG TV,300";
        
        String fileName = "fileTest.txt";
        
        producerTemplate.sendBodyAndHeader(environment.getProperty("fromRoute"), message, Exchange.FILE_NAME, fileName);
        
        Thread.sleep(3000);
        
        File outFile = new File("data/output/"+fileName);
        Assert.assertTrue(outFile.exists());
    }
}
