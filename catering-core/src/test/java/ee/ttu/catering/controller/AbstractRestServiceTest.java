package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ee.ttu.catering.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("unittest")
public abstract class AbstractRestServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	public AbstractRestServiceTest() {
	}

	abstract String getServiceMapping();
	
	abstract String getCreateContent();
	
	abstract String getUpdateContent();
	
	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired 
	protected WebApplicationContext wac;
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	MockMvc mvc;
	
	@PostConstruct
	public void mocMvc() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
   
    protected int id;
    
    @Before
    public void setup() throws Exception{
       id = createEntity();
    }
    
    private Integer createEntity() {
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
    public void testRead() {
    	try {
    		mvc.perform(MockMvcRequestBuilders.get(getServiceMapping() + id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    		
    	} catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    
    @Test
    public void testUpdate() {
    	try {
    		mvc.perform(MockMvcRequestBuilders.put(getServiceMapping() + id)
    				.contentType(MediaType.APPLICATION_JSON)
    				.accept(MediaType.APPLICATION_JSON)
    				.content(getUpdateContent()))
    				.andExpect(status().isOk())
    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    		
    	} catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    
    @Test
    public void testDelete() {
    	try {
    		mvc.perform(MockMvcRequestBuilders.delete(getServiceMapping() + id)
    				.contentType(MediaType.APPLICATION_JSON))
    				.andExpect(status().isNoContent());
    		
    	} catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    
	private Integer parseId(String response) throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response);
     
        return actualObj.findValue("id").asInt();
	}

	protected String toJSONString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString( obj);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Bad input object");
		}
	}
}
