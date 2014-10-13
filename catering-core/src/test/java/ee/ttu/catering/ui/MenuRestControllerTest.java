package ee.ttu.catering.ui;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.web.WebDelegatingSmartContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ee.ttu.catering.config.unittest.UnitTestEnv;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = WebDelegatingSmartContextLoader.class, classes = UnitTestEnv.class)
public class MenuRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	Logger LOG = Logger.getLogger(this.getClass());
	
    public MenuRestControllerTest() {}

    MockMvc mvc;
     
    @Autowired WebApplicationContext wac;
    
    
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

    @Test
    public void userCanLoginByUsername() {
      open("RestApi/menu/");
      $(By.name("user.name")).setValue("johny");
      $("#submit").click();
      $(".loading_progress").should(disappear); // Waits until element disappears
      $("#username").shouldHave(text("Hello, Johny!")); // Waits until element gets text
    }
     
}