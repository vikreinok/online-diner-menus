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
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ee.ttu.catering.config.unittest.UnitTestEnv;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = UnitTestEnv.class)
public class MenuRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String MAPPING = "/menu/";

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
        	
    final String CONTENT = "{\"name\":\"test\"}";
	mvc.perform(MockMvcRequestBuilders.post(MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(CONTENT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
       
    final String BAD_CONTENT = "{\"nam\":\"Wrong field value and field content\"}";
	result = mvc.perform(MockMvcRequestBuilders.post("/menu")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(BAD_CONTENT))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
	
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    @Transactional
    public void testMenuDelete() {
    	try{
    		String CONTENT = "{\"id\":2,\"name\":\"test\"}";
    		
    		mvc.perform(MockMvcRequestBuilders.post(MAPPING)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(CONTENT))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn();
    		
    	    mvc.perform(MockMvcRequestBuilders.delete(MAPPING + "2")
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
    				.andReturn(); 
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
}