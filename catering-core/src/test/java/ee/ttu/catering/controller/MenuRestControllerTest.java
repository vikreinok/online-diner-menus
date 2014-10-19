package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ee.ttu.catering.config.unittest.UnitTestEnv;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = UnitTestEnv.class)
public class MenuRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	Logger LOG = Logger.getLogger(this.getClass());
	
    public MenuRestControllerTest() {}

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
                .content("{\"nam\":\"Wrong field value and field content\"}"))
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
    		String CONTENT = "{\"id\":2,\"entityVersion\":0,\"name\":\"test\",\"created\":null}";
    		
    		result = mvc.perform(MockMvcRequestBuilders.post("/menu/create")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.content(CONTENT))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn();
    		
			mvc.perform(MockMvcRequestBuilders.get("/menu/read/2")
					.accept(MediaType.APPLICATION_JSON))
			        .andExpect(MockMvcResultMatchers.status().isOk())
			        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
			        .andExpect(MockMvcResultMatchers.content().string(CONTENT));
    		
    		result = mvc.perform(MockMvcRequestBuilders.post("/menu/delete/2")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.andExpect(content().string(Contains))
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