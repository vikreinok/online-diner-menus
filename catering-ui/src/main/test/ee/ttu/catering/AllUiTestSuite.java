package ee.ttu.catering;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ee.ttu.catering.ui.diner.AddDinerUiTest;
import ee.ttu.catering.ui.diner.DeleteDinerUiTest;
import ee.ttu.catering.ui.diner.DinerDetailViewForNotLoggedInUiTest;
import ee.ttu.catering.ui.diner.DinerListUiTest;
import ee.ttu.catering.ui.diner.ModifyDinerUiTest;
import ee.ttu.catering.ui.general.FastSearchUiTest;
import ee.ttu.catering.ui.menu.AddMenuUiTest;
import ee.ttu.catering.ui.menu.DeleteMenuUiTest;
import ee.ttu.catering.ui.menu.MenuDetailViewForNotLoggedInUser;
import ee.ttu.catering.ui.menu.MenuListUiTest;
import ee.ttu.catering.ui.menu.ModifyMenuUiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({

	DinerDetailViewForNotLoggedInUiTest.class,
	AddDinerUiTest.class,
	DeleteDinerUiTest.class,
	DinerListUiTest.class,
	ModifyDinerUiTest.class,
	FastSearchUiTest.class,
	MenuDetailViewForNotLoggedInUser.class,
	AddMenuUiTest.class,
	DeleteMenuUiTest.class,
	MenuListUiTest.class,
	ModifyMenuUiTest.class
})
public class AllUiTestSuite {   
}  