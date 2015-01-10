package dinerFunctionalTests;

import static com.codeborne.selenide.Selenide.sleep;

import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Condition;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.ui.diner.DinerUiForm;

public class DeleteButtonTest {
	
	private String dinerId;
	
	@BeforeClass
	public static void signIn(){
		new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testDeleteButtonVisibility(){
		DinerUiForm form = new DinerUiForm();
		sleep(1000);
		form.clickAddDinerTab();
		form.deleteButton().shouldNotBe(Condition.visible);
		form.setDinerData("Burgerite kodu", "Rasvased söögid");
		form.clickSaveButton();
		sleep(1000);
		dinerId = form.extractDigits(form.getCurrentUrl()).get(1).trim();
		form.deleteButton().shouldBe(Condition.visible);
		form.clickLogOut();
		sleep(5000);
		testDeleteButtonVisibilityWithNoLogIn(form);
	}
	
	
	public void testDeleteButtonVisibilityWithNoLogIn(DinerUiForm form){
		form.openForm("catering-ui/#diner/"+dinerId);
		sleep(1000);
		form.deleteButton().shouldNotBe(Condition.visible);
	}

}
