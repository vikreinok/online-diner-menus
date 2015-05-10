package ee.ttu.catering.controller.suit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ee.ttu.catering.controller.DinerControllerTest;
import ee.ttu.catering.controller.LoginControllerTest;
import ee.ttu.catering.controller.MenuControllerTest;
import ee.ttu.catering.controller.MenuItemControllerTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   DinerControllerTest.class,
   MenuControllerTest.class,
   MenuItemControllerTest.class,
   LoginControllerTest.class
})
public class ControllerTestSuite {
	
}  	