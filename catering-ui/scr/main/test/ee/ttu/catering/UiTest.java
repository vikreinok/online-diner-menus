package ee.ttu.catering;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class UiTest {

	@Before
	public void testLogin() {
		open("/catering-ui/#login");
		sleep(200);
		$(By.name("username")).sendKeys("admin");
		sleep(50);
		$(By.name("password")).sendKeys("admin");
		sleep(50);
		$("#submitLoginButtom").click();
		sleep(500);
	}
	
	@Test
	public void testAddDiner() {
	  open("/catering-ui/#diners/add");
	  sleep(300);
	  $(By.name("name")).sendKeys("1234");
	  sleep(100);
	  $(By.id("description")).sendKeys("1234descri");
	  sleep(100);
	  $("#saveDinerButton").click();
	  sleep(1000);
//	  $("#username").shouldHave(text("Hello, Johny!"));
//	  $(".error").shouldNotBe(visible);
	}
}
