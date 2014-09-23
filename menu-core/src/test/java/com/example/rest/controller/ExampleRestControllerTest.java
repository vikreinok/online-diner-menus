/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest.controller;

import com.example.config.WebAppConfig;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author craigbrookes
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = WebAppConfig.class)
public class ExampleRestControllerTest {
    
    public ExampleRestControllerTest() {}

    MockMvc mvc;
     
    @Autowired WebApplicationContext wac;
    
     @Before
    public void setup(){
       mvc = MockMvcBuilders.webAppContextSetup(wac).build();
     }

    @Test
    public void testExampleCreate() {
        MvcResult result;
        try{
       mvc.perform(MockMvcRequestBuilders.post("/example/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
       
       result = mvc.perform(MockMvcRequestBuilders.post("/example/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"nam\":\"test\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
            System.out.println("RESULT " + result.getResponse().getContentAsString());
       
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
        
    }
}