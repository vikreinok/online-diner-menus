package ee.ttu.catering.controller;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;


public class MenuControllerTest extends AbstractRestServiceTest {

    private static final String CONTENT = "{\"id\": 1000, \"name\":\"test\"}";
	private final String MAPPING = "/menu/";
    
    @Override
    String getServiceMapping() {
    	return MAPPING;
    }
    
    @Override
    String getCreateContent() {
    	return CONTENT;
    }
    
    @Override
    String getUpdateContent() {
    	return CONTENT;
    }
    
    @Test
    @Transactional
    public void testMenuCreate() {
        try{
        
	    final String CONTENT = "{\"name\":\"test\"}";
		mvc.perform(MockMvcRequestBuilders.post(MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(CONTENT))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
       
       
	    final String BAD_CONTENT = "{\"nam\":\"Wrong field value and field content\"}";
		mvc.perform(MockMvcRequestBuilders.post("/menu")
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
    


}