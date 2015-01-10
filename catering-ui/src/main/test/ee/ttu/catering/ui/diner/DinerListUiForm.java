package ee.ttu.catering.ui.diner;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import ee.ttu.catering.BaseUiForm;

public class DinerListUiForm extends BaseUiForm{
	
	public SelenideElement getTestDiner(String dinerName){
		return $(By.linkText(dinerName));
	}

}
