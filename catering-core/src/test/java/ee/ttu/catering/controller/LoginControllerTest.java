package ee.ttu.catering.controller;

import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import ee.ttu.catering.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("unittest")
public class LoginControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	Logger LOG = Logger.getLogger(this.getClass());
	
    public LoginControllerTest() {}

    MockMvc mvc;
    @Autowired WebApplicationContext wac;
    
    @Before
    public void setup(){
       mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    public static final String CONTENT = "admin:admin";
    @Test
    public void testLogin() {
        try{
	        mvc.perform(MockMvcRequestBuilders.post("/rest/login/")
	            .contentType(MediaType.ALL)
	            .accept(MediaType.ALL)
	            .content(Base64.encode(CONTENT.getBytes())))
	            .andExpect(header().string("x-token", anything()))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
	            .andReturn(); 
   
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testLogout() {
    	try{
    		mvc.perform(MockMvcRequestBuilders.delete("/rest/logout")
				.contentType(MediaType.ALL)
				.accept(MediaType.ALL))
				.andExpect(status().isOk())
				.andReturn();
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }

 
}