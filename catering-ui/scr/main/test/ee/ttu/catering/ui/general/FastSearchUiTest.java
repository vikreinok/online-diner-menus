package ee.ttu.catering.ui.general;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;

public class FastSearchUiTest {
	
	private static int dinerId;
	private boolean hasLoggedIn = false;
	
	@BeforeClass
	public static void createTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		dinerId = functions.createTestDiner("Test kirjeldus", "Test restoran");	
		
	}
	

	@Test
	public void searchTestDinerWithFastSearch(){
		hasLoggedIn = true;
		new UiTest().loginAsAdminUser();
		GeneralForm form = new GeneralForm();
		form.fastSearchDiner("Test restoran");
		form.clickSearchResult();
		assertEquals("http://localhost:8080/catering-ui/#diner/"+ dinerId, form.getCurrentUrl());
		form.clickLogOut();
		
	}
	
	@Test
	public void searchTestDinerWithFastSearchNoLogIn(){
		GeneralForm form = new GeneralForm();
		if(hasLoggedIn){
			form.clickLogOut();
		}
		
		form.openForm("catering-ui/");
		form.fastSearchDiner("Test restoran");
		form.clickSearchResult();
		assertEquals("http://localhost:8080/catering-ui/#diner/"+ dinerId, form.getCurrentUrl());
	}
	
	@AfterClass
	public static void deleteTestData(){
		DatabaseFunctions functions = new DatabaseFunctions();
		functions.deleteTestData(Integer.toString(dinerId), "DINER");
	}

}
