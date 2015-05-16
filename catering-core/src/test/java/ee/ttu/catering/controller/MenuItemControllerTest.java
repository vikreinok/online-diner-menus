package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
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

import ee.ttu.catering.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("unittest")
public class MenuItemControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	Logger LOG = Logger.getLogger(this.getClass());
	
    public MenuItemControllerTest() {}

    MockMvc mvc;
     
    @Autowired WebApplicationContext wac;
    
    
    @Before
    public void setup(){
       mvc = MockMvcBuilders.webAppContextSetup(wac).build();
     }

    @Test
    @Ignore
    public void testMenuItemCreate() {
        try{
        final String CONTENT = "{\"name\":\"test\"}";
		mvc.perform(MockMvcRequestBuilders.post("/rest/menu_item/")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(CONTENT))
                 .andExpect(status().isOk())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                 .andReturn(); 
        
        
        final String BAD_CONTENT = "{\"nam\":\"Wrong name and field value\"}";
		mvc.perform(MockMvcRequestBuilders.post("/rest/menu_item/")
                 .contentType(MediaType.APPLICATION_JSON)
                 .accept(MediaType.APPLICATION_JSON)
                 .content(BAD_CONTENT))
                 .andExpect(status().isBadRequest())
                 .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                 .andReturn(); 
       
        } catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
}