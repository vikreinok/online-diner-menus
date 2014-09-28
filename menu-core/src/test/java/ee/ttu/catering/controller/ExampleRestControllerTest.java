/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ee.ttu.catering.config.WebAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = WebAppConfig.class)
public class ExampleRestControllerTest {
    
	Logger LOG = Logger.getLogger(this.getClass());
	
    public ExampleRestControllerTest() {}

    MockMvc mvc;
     
    @Autowired WebApplicationContext wac;
    
     @Before
    public void setup(){
       mvc = MockMvcBuilders.webAppContextSetup(wac).build();
     }

    @Test
    @Transactional
    public void testMenuCreate() {
        MvcResult result;
        try{
       mvc.perform(MockMvcRequestBuilders.post("/menu/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"test\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
       
       result = mvc.perform(MockMvcRequestBuilders.post("/menu/create")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"nam\":\"test\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
       LOG.info("RESULT " + result.getResponse().getContentAsString());
       
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    @Transactional
    public void testMenuDelete() {
    	MvcResult result;
    	try{
    		String CONTENT = "{\"id\":\"1\",\"name\":\"test\"}";
    		
			mvc.perform(MockMvcRequestBuilders.post("/menu/create")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.content(CONTENT))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn(); 
    		
    		
    		result = mvc.perform(MockMvcRequestBuilders.post("/menu/delete/1")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.content(""))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn(); 
    		
    	    LOG.info("RESULT " + result.getResponse().getContentAsString());
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
}