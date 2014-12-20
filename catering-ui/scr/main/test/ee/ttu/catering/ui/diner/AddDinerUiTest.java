package ee.ttu.catering.ui.diner;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;

public class AddDinerUiTest {
	
	private String dinerId;
	
	
	@Before
	public void signIn() {
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testAddDiner(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Teisipäev", "Hõrgud road");
		form.clickSaveButton();
		sleep(300);
		dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		assertEquals("Success! Diner saved successfully",form.successMessage());
		form.clickLogOut();
	}
	
	@After
	public void deleteTestData(){
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		dbFunctions.deleteTestData(dinerId, "DINER");
	}

}
