package ee.ttu.catering;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import org.openqa.selenium.By;

public class UiTest {

	public void loginAsAdminUser() {
		sleep(1000);
		open("/catering-ui/#login");
		sleep(500);
		$(By.name("username")).sendKeys("admin");
		sleep(500);
		$(By.name("password")).sendKeys("admin");
		sleep(500);
		$("#submitLoginButtom").click();
		sleep(500);
	}
	

}
