package ee.ttu.catering.ui.menu;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import ee.ttu.catering.BaseUiForm;

public class MenuUiForm extends BaseUiForm{
	
	public MenuUiForm setMenuData(String name){
		$(By.id("name")).setValue(name);
		return this;
	}
	
	public MenuUiForm clickSaveButton(){
		$(By.id("saveMenuButton")).click();
		return this;
	}
	
	public MenuUiForm clickDeleteButtonAndConfirm(){
		$(By.id("deleteMenuButton")).click();
		sleep(500);
	    $(By.xpath("//button[contains(@class,'btn btn-warning delete')]")).click();
		return this;
	}
	


}
