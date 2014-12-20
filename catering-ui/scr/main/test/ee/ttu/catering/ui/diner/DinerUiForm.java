package ee.ttu.catering.ui.diner;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import ee.ttu.catering.BaseUiForm;
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
	
	public DinerUiForm clickDeleteButton(){
		$(By.id("deleteDinerButton")).click();
		return this;
	}
	
	

	

}
