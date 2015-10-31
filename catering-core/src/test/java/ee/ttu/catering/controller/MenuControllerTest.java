package ee.ttu.catering.controller;

import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.model.MenuItem;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MenuControllerTest extends AbstractRestServiceTest {

    private static final String CONTENT = "{\"name\":\"test\"}";
    private static final String UPDATE_CONTENT = "{\"name\":\"test2\"}";
	private final String MAPPING = "/rest/menu/";
    
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
    	return UPDATE_CONTENT;
    }
    
    @Test
    public void testAddMenuItem() {
        try{
        	
    	MenuItem menuItem = new MenuItem();
    	menuItem.setName("Food");
    	menuItem.setPrice(2.99);
    	
        Menu menu = new Menu();
        menu.setName("Monday menu");
        menu.setId(1);
		menu.setMenuItems(Arrays.asList(menuItem));
        
		mvc.perform(MockMvcRequestBuilders.post(MAPPING)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJSONString(menu)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
	
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testAddComment() {
        try{
        	
    	MenuComment comment = new MenuComment();
		comment.setComment("test");
        
		mvc.perform(MockMvcRequestBuilders.post(MAPPING + "comment/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJSONString(comment)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn(); 
	
        }catch(Exception e){
            e.printStackTrace();
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testMenuCreate() {
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
		mvc.perform(MockMvcRequestBuilders.post(MAPPING)
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