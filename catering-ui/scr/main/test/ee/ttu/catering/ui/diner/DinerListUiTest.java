package ee.ttu.catering.ui.diner;

import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.Selenide.sleep;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.general.GeneralForm;

public class DinerListUiTest {
	
	private boolean hasLoggedIn = false;
	
	private static String dinerName = "Listi testimine";
	
	private static int dinerId;
	
	@BeforeClass
	public static void createTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		dinerId = functions.createTestDiner("2014-12-21","Test kirjeldus","2014-12-21", dinerName);	
	}
	
	@Test
	public void testDinerListWithNoLogging(){
		GeneralForm form = new GeneralForm();
		if(hasLoggedIn){
			form.clickLogOut();
		}
		form.openForm("catering-ui/");
		form.clickDinersTab();
		DinerListUiForm dinerListForm = new DinerListUiForm();
		assertEquals(dinerName, dinerListForm.getTestDiner(dinerName).getText());
		System.out.println("LALALALALAL");
		
	}
	
	@Test
	public void testDinerListWithLoggedIn(){
		hasLoggedIn = true;
		new UiTest().loginAsAdminUser();
		GeneralForm form = new GeneralForm();
		form.clickDinersTab();
		DinerListUiForm dinerListForm = new DinerListUiForm();
		assertEquals(dinerName, dinerListForm.getTestDiner(dinerName).getText());
		sleep(5000);
		form.clickLogOut();
		sleep(5000);
	}
	
	@AfterClass
	public static void deleteTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		functions.deleteTestData(Integer.toString(dinerId), "DINER");
	}

}
