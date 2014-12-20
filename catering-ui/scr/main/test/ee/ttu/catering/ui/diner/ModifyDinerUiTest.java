package ee.ttu.catering.ui.diner;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;

public class ModifyDinerUiTest {
	
	private String dinerId;
	
	@Before
	public void signInAndCreateTestData() {
		new UiTest().loginAsAdminUser();
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Kolmapäev", "Hõrgud road");
		form.clickSaveButton();
		sleep(1000);
		dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		sleep(300);
	}
	
	@Test
	public void modifyDiner(){
		DinerUiForm form = new DinerUiForm();
		form.openForm("catering-ui/#diner/"+ dinerId);
		sleep(1000);
		form.setDinerData("Muudetud nimi", "Uued praed");
		form.clickSaveButton();
		sleep(500);
		assertEquals("Success! Diner saved successfully",form.successMessage());
		
	}
	
	@After
	public void deleteTestData(){
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		dbFunctions.deleteTestData(dinerId);
		
	}
	
	

}
