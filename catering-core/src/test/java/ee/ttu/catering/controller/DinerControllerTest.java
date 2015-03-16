package ee.ttu.catering.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class DinerControllerTest extends AbstractRestServiceTest {

    private static final String CONTENT = "{\"id\": 1000, \"name\":\"test\", \"description\":\"test1234123\"}";
	private final String MAPPING = "/diner/";
    
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
    public void testFindMenusByDinerId() {
        try{
        
		mvc.perform(MockMvcRequestBuilders.get(MAPPING + "menus/" + 0)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
	
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    

}