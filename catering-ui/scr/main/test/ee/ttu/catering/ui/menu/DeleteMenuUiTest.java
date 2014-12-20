package ee.ttu.catering.ui.menu;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;

public class DeleteMenuUiTest {
	
	
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
		form.clickMenusTab();
	}
	
	@Test
	public void deleteMenu(){
		MenuUiForm form = new MenuUiForm();
		form.openForm("catering-ui/#menu/"+ menuId);
		sleep(1500);
		form.clickDeleteButtonAndConfirm();
		sleep(500);
		assertEquals("http://localhost:8080/catering-ui/#menus", form.getCurrentUrl());
		form.clickLogOut();
	}
	
	
	

}
