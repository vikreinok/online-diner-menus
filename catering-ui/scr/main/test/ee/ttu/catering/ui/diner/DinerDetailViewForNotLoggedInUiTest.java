package ee.ttu.catering.ui.diner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;
import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.general.GeneralForm;

public class DinerDetailViewForNotLoggedInUiTest {
	
	private static int dinerId;
	
	private static String dinerName = "Ameeriklased";
	
	@BeforeClass
	public static void createTestDiner(){
		DatabaseFunctions functions = new DatabaseFunctions();
		dinerId = functions.createTestDiner("2014-12-21","Test kirjeldus","2014-12-21", dinerName);	
	}
	
	@Test
	public void testDinerDetailViewForNotLoggedInUser(){
		GeneralForm genForm = new GeneralForm();
		genForm.openForm("catering-ui/");
		DinerUiForm form = new DinerUiForm();
		form.clickDinersTab();
		sleep(1000);
		DinerListUiForm dinerListForm = new DinerListUiForm();
		dinerListForm.getTestDiner(dinerName).click();
		sleep(1000);
		assertEquals("http://localhost:8080/catering-ui/#diner/"+dinerId, form.getCurrentUrl());
		
	}
	
	@AfterClass
	public static void deleteTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		functions.deleteTestData(Integer.toString(dinerId), "DINER");
	}
	
	

}
