package dinerFunctionalTests;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.diner.DinerUiForm;

public class BoundaryTestsForDinerName {
	
	private String dinerId;
	
	@BeforeClass
	public static void signIn(){
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testSaveWithNoDinerName(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("", "test kirjeldus");
		form.clickSaveButton();
		assertEquals("http://localhost:8080/catering-ui/#diner/add", form.getCurrentUrl());
	}
	
	@Test
	public void testSaveWith1LetterDinerName(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("K", "test kirjeldus");
		form.clickSaveButton();
		assertEquals("http://localhost:8080/catering-ui/#diner/add", form.getCurrentUrl());
	}
	
	@Test
	public void testSaveWith21LetterDinerName(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Suur mõnus kohtBBBBBB", "test kirjeldus");
		form.clickSaveButton();
		assertEquals("http://localhost:8080/catering-ui/#diner/add", form.getCurrentUrl());
	}
	
	@Test
	public void testSaveWith2LetterDinerName(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("BQ", "test kirjeldus");
		form.clickSaveButton();
		sleep(1000);
		dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		assertEquals("http://localhost:8080/catering-ui/#diner/"+dinerId, form.getCurrentUrl());
		deleteTestData();
	}
	
	@Test
	public void testSaveWith20LetterDinerName(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Suur mõnus kohtBBBBQ", "test kirjeldus");
		form.clickSaveButton();
		sleep(1000);
		dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		assertEquals("http://localhost:8080/catering-ui/#diner/"+dinerId, form.getCurrentUrl());
		deleteTestData();
	}
	
	public void deleteTestData(){
		DatabaseFunctions dbFunctions = new DatabaseFunctions();
		dbFunctions.deleteTestData(dinerId, "DINER");
	}
	
	

}
