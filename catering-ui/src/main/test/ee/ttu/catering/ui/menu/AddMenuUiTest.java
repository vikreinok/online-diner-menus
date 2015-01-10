package ee.ttu.catering.ui.menu;

import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.sleep;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;

public class AddMenuUiTest {
	
	private String menuId;
	
	@Before
	public void signIn() {
        new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testAddMenu(){
		MenuUiForm form = new MenuUiForm();
		sleep(500);
		form.openForm("catering-ui/#menu/add");
		form.setMenuData("Esmaspäev");
		form.clickSaveButton();
		sleep(5000);
		menuId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		assertEquals("Success! Menu saved successfully", form.successMessage());
		form.clickLogOut();
	}
	
	@After
	public void deleteTestData(){
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		dbFunctions.deleteTestData(menuId, "MENU");
	}

}
