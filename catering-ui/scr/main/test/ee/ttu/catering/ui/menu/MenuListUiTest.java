package ee.ttu.catering.ui.menu;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.general.GeneralForm;

public class MenuListUiTest {
	
	private static int menuId;
	private boolean hasLoggedIn = false;
	private static String menuName = "BBQ grillid";
	
	@BeforeClass
	public static void createTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		menuId = functions.createTestMenu(menuName);
	}
	
	@Test
	public void testMenuListWithNoLogging(){
		GeneralForm form = new GeneralForm();
		if(hasLoggedIn){
			form.clickLogOut();
		}
		form.openForm("catering-ui/");
		form.clickMenusTab();
		MenuListUiForm listForm = new MenuListUiForm();
		assertEquals(menuName, listForm.getTestMenu(menuName).getText());
	}
	
	
	@Test
	public void testMenuListWithLogging(){
		hasLoggedIn = true;
		new UiTest().loginAsAdminUser();
		GeneralForm form = new GeneralForm();
		form.clickMenusTab();
		MenuListUiForm listForm = new MenuListUiForm();
		assertEquals(menuName, listForm.getTestMenu(menuName).getText());
		sleep(5000);
		form.clickLogOut();
		sleep(5000);
	}
	
	@AfterClass
	public static void deleteTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		functions.deleteTestData(Integer.toString(menuId), "MENU");
	}

}
