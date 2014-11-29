package ee.ttu.catering.controller;


public class DinerControllerTest extends AbstractRestServiceTest {

    private static final String CONTENT = "{\"name\":\"test\"}";
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
    

}