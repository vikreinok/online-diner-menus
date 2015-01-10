package ee.ttu.catering.ui.diner;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;

public class DeleteDinerUiTest {
	
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
		form.clickDinersTab();
	}
	
	@Test
	public void deleteDiner(){
		DinerUiForm form = new DinerUiForm();
		form.openForm("catering-ui/#diner/"+ dinerId);
		sleep(1000);
		form.clickDeleteButtonAndConfirm();
		sleep(300);
		assertEquals("http://localhost:8080/catering-ui/#diners", form.getCurrentUrl());
		form.clickLogOut();
	}
	
	

}
