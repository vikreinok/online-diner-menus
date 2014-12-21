package dinerFunctionalTests;

import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.db.DatabaseFunctions;
import ee.ttu.catering.ui.diner.DinerUiForm;

public class BoundaryTestsForDescription {
	
	private String dinerId;
	
	@BeforeClass
	public static void signIn(){
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testSaveWithNoDescription(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Rootsi restoran", "");
		form.clickSaveButton();
		assertEquals("http://localhost:8080/catering-ui/#diner/add", form.getCurrentUrl());
	}
	
	@Test
	public void testSaveWith9LetterDescription(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Hiina restoran", "Mõnsad as");
		form.clickSaveButton();
		assertEquals("http://localhost:8080/catering-ui/#diner/add", form.getCurrentUrl());
	}
	
	@Test
	public void testSaveWith51LetterDescription(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Ülikooli kohvik", "Eriti mõnusad ja suured asjad nagu need alati olnud");
		form.clickSaveButton();
		assertEquals("http://localhost:8080/catering-ui/#diner/add", form.getCurrentUrl());
	}
	
	@Test
	public void testSaveWith10LetterDescription(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("BQ", "Karmid söö");
		form.clickSaveButton();
		sleep(1000);
		dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		assertEquals("http://localhost:8080/catering-ui/#diner/"+dinerId, form.getCurrentUrl());
		deleteTestData();
	}
	
	@Test
	public void testSaveWith50LetterDinerName(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.setDinerData("Suur mõnus kohtBBBBQ", "Eriti mõnusad ja suured asjad nagu need alati olnu");
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
