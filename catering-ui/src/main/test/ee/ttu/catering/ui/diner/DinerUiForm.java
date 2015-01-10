package ee.ttu.catering.ui.diner;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import ee.ttu.catering.BaseUiForm;
import ee.ttu.catering.ui.menu.MenuUiForm;
public class DinerUiForm extends BaseUiForm{

	public DinerUiForm setDinerData(String dinerName, String dinerDescription){
		$(By.id("name")).setValue(dinerName);
		$(By.id("description")).setValue(dinerDescription);
		return this;
	}
	
	public DinerUiForm clickSaveButton(){
		$(By.id("saveDinerButton")).click();
		return this;
	}

	
	public DinerUiForm clickDeleteButtonAndConfirm(){
		deleteButton().click();
		sleep(500);
	    $(By.xpath("//button[contains(@class,'btn btn-warning delete')]")).click();
		return this;
	}
	
	public SelenideElement deleteButton(){
		return $(By.id("deleteDinerButton"));
	}
	
	public String getHeadLine(){
		return $(By.xpath("//div[@class='page-header']/h1")).getText();
	}

}
