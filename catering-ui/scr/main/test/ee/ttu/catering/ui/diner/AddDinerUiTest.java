package ee.ttu.catering.ui.diner;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;

public class AddDinerUiTest {
	
	@Before
	public void signIn() {
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testAddDiner(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.openForm("catering-ui/#diner/add");
		form.setDinerData("Teisipäev", "Hõrgud road");
		form.clickSaveButton();
		sleep(300);
		assertEquals("Success! Diner saved successfully",form.successMessage());
	}

}
