package ee.ttu.catering.ui.menu;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;

public class ModifyMenuUiTest {
	
	private String menuId;
	
	@Before
	public void signInAndCreateTestData() {
		new UiTest().loginAsAdminUser();
		MenuUiForm form = new MenuUiForm();
		sleep(1000);
		form.clickAddMenuTab();
		form.setMenuData("Test menüü");
		form.clickSaveButton();
		sleep(1000);
		menuId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		sleep(300);
	}
	
	@Test
	public void modifyMenu(){
		MenuUiForm form = new MenuUiForm();
		form.openForm("catering-ui/#menu/"+ menuId);
		sleep(1000);
		form.setMenuData("Muudetud menüü");
		form.clickSaveButton();
		sleep(500);
		assertEquals("Success! Menu saved successfully",form.successMessage());
		sleep(500);
		form.clickLogOut();
		
	}
	
	@After
	public void deleteTestData(){
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		dbFunctions.deleteTestData(menuId, "MENU");
		
	}

}
