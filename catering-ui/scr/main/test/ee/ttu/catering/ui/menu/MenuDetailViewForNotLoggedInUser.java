package ee.ttu.catering.ui.menu;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.general.GeneralForm;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

public class MenuDetailViewForNotLoggedInUser {
	
    private static int menuId;
	
	private static String menuName = "Ainult liha";
	
	@BeforeClass
	public static void createTestDiner(){
		DatabaseFunctions functions = new DatabaseFunctions();
		menuId = functions.createTestMenu(menuName);
	}
	
	@Test
	public void testMenuDetailViewAccessWithNoLogIn(){
		GeneralForm genForm = new GeneralForm();
		genForm.openForm("catering-ui/");
		genForm.clickMenusTab();
		sleep(1000);
		MenuListUiForm listForm = new MenuListUiForm();
		listForm.getTestMenu(menuName).click();
		sleep(1000);
		MenuUiForm menuForm = new MenuUiForm();
		assertEquals("http://localhost:8080/catering-ui/#menu/"+menuId, menuForm.getCurrentUrl());
	}
	
	@AfterClass
	public static void deleteTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		functions.deleteTestData(Integer.toString(menuId), "MENU");
	}
	

}
