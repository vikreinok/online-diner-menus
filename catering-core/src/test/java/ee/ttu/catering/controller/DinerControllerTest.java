package ee.ttu.catering.controller;


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
    

}