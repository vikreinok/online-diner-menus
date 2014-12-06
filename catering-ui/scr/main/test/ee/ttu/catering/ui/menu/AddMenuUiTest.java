package ee.ttu.catering.ui.menu;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ttu.catering.UiTest;

public class AddMenuUiTest {
	
	@Before
	public void signIn() {
        new UiTest().loginAsAdminUser();
	}
	
	@Test
	public void testAddMenu(){
		MenuUiForm form = new MenuUiForm();
		form.openForm("catering-ui/#menu/add");
		form.setMenuData("Esmaspäev");
		form.clickSaveButton();
		assertEquals("Success! Menu saved successfully", form.successMessage());
	}

}
