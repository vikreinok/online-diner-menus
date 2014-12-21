package dinerFunctionalTests;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.diner.DinerUiForm;

public class LastModifiedAndCreatedTest {
	
	@BeforeClass
	public static void signIn(){
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testCreatedAndLastModifiedBySavingNewDiner(){
		Date today = new Date();
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Sushi baar", "Riis ja kala");
		form.clickSaveButton();
		sleep(1000);
		String dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		String formattedDate = form.formatDate(today).replaceAll("-", " - ");
		assertEquals(formattedDate, form.getModifiedDate().trim());
		assertEquals(formattedDate, form.getAddDate().trim());
		deleteTestData(dinerId);
	}
	
	@Test
	public void testThatModifiedChangedToTodayAfterPeriod(){
		Date today = new Date();
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		int dinerID = createTestDiner();
		form.openForm("catering-ui/#diner/"+dinerID);
		sleep(1000);
		form.setDinerData("McDonalds", "Friikad");
		form.clickSaveButton();
		sleep(1000);
		String formattedDate = form.formatDate(today).replaceAll("-", " - ");
		assertEquals(formattedDate, form.getModifiedDate().trim());
		assertEquals("2014 - 12 - 14", form.getAddDate().trim());
		deleteTestData(Integer.toString(dinerID));
	}
	
	private int createTestDiner(){
		DatabaseFunctions functions = new DatabaseFunctions();
		return functions.createTestDiner("2014-12-14", "Odavat toidud", "2014-12-14", "Hakkliha");
	}
	
	public void deleteTestData(String dinerId){
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		dbFunctions.deleteTestData(dinerId, "DINER");
	}

}
