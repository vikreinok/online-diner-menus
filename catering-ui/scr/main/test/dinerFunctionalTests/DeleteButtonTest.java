package dinerFunctionalTests;

import static com.codeborne.selenide.Selenide.sleep;

import org.junit.BeforeClass;
import org.junit.Test;

import com.codeborne.selenide.Condition;

import ee.ttu.catering.UiTest;
import ee.ttu.catering.ui.diner.DinerUiForm;

public class DeleteButtonTest {
	
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
		form.deleteButton().shouldBe(Condition.visible);
	}

}
