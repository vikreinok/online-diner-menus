package ee.ttu.catering.ui.menu;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
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
	
	public MenuUiForm clickDeleteButton(){
		$(By.id("deleteMenuButton")).click();
		return this;
	}
	


}
