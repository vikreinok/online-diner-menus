package ee.ttu.catering.ui.menu;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

import ee.ttu.catering.BaseUiForm;

public class MenuListUiForm extends BaseUiForm{
	
	public SelenideElement getTestMenu(String menuName){
		return $(By.linkText(menuName));
	}

}
