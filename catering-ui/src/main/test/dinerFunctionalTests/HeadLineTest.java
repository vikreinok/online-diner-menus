package dinerFunctionalTests;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.ui.diner.DinerUiForm;

public class HeadLineTest {
	
	@BeforeClass
	public static void signIn(){
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testHeadLineAfterSave(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Vapiano", "Pizzad ja pastad");
		form.clickSaveButton();
		sleep(1000);
		assertEquals("Diner details Vapiano", form.getHeadLine());
	}

}
