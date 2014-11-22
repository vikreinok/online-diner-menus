package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ee.ttu.catering.config.unittest.UnitTestEnv;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = UnitTestEnv.class)
public abstract class AbstractRestServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	public AbstractRestServiceTest() {
	}

	ObjectMapper mapper = new ObjectMapper();
	
	abstract String getServiceMapping();
	
	abstract String getCreateContent();
	abstract String getUpdateContent();
	
	Logger LOG = Logger.getLogger(this.getClass());

	protected void mocMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
    @Autowired WebApplicationContext wac;
    MockMvc mvc;

    @Before
    public void setup() throws Exception{
       mocMvc();
       create();
    }
    
    private Integer create() {
    	try{
    		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(getServiceMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getCreateContent()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    	
			return parseId(result.getResponse().getContentAsString());
    	}catch(Exception e){
    		fail(e.getMessage());
        }
    	return 0;
	}

	@Test
    @Order(0)
    @Transactional
    public void testCreate() {
        try{
        	 mvc.perform(MockMvcRequestBuilders.post(getServiceMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getCreateContent()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        	
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    
    @Test
    @Order(1)
    @Transactional
    public void testRead() {
    	try{
    		mvc.perform(MockMvcRequestBuilders.get(getServiceMapping(), create())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    
    @Test
    @Order(2)
    @Transactional
    public void testUpdate() {
    	try{
    		mvc.perform(MockMvcRequestBuilders.put(getServiceMapping() + create())
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.content(getCreateContent()))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    @Test
    @Order(3)
    @Transactional
    public void testDelete() {
    	try{
    		mvc.perform(MockMvcRequestBuilders.delete(getServiceMapping() + create())
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    		
    	}catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    
	private Integer parseId(String response) throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response);
     
        JsonNode result = actualObj.get("result");
		return result.findValue("id").asInt();
	}
}
