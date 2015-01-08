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
import org.springframework.transaction.annotation.Isolation;
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
       id = create();
    }
    
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    private Integer create() {
    	try{
    		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(getServiceMapping())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getCreateContent()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    	
    		entityManager.flush();
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
    @Order(2)
    @Transactional(isolation = Isolation.DEFAULT)
    public void testUpdate() {
    	try {
    		
    		Thread.sleep(1000);
    		mvc.perform(MockMvcRequestBuilders.put(getServiceMapping() + id)
    				.contentType(MediaType.APPLICATION_JSON)
//    				.accept(MediaType.APPLICATION_JSON)
    				.content(getCreateContent()))
    				.andExpect(status().isOk());
//    				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    		
    	} catch(Exception e){
    		e.printStackTrace();
    		fail(e.getMessage());
    	}
    }
    @Test
    @Order(3)
    @Transactional
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
}
