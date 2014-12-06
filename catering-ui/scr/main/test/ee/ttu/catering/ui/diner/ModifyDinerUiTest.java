package ee.ttu.catering.ui.diner;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.helpFunctions.HelpFunctions;

public class ModifyDinerUiTest {
	
	private String dinerId;
	
	@Before
	public void signInAndCreateTestData() {
		new UiTest().loginAsAdminUser();
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.openForm("catering-ui/#diner/add");
		form.setDinerData("Kolmapäev", "Hõrgud road");
		form.clickSaveButton();
		sleep(1000);
		dinerId = new HelpFunctions().extractDigits(form.getCurrentUrl()).get(1).trim();
		sleep(300);
	}
	
	@Test
	public void modifyDiner(){
		DinerUiForm form = new DinerUiForm();
		form.openForm("catering-ui/#diner/"+ dinerId);
		sleep(1000);
		form.setDinerData("Muudetud nimi", "Uued praed");
		form.clickSaveButton();
		assertEquals("Success! Diner saved successfully",form.successMessage());
		
	}
	
	

}
