package ee.ttu.catering.rest.controller.jsondoc;

import org.jsondoc.core.annotation.ApiFlow;
import org.jsondoc.core.annotation.ApiFlowSet;
import org.jsondoc.core.annotation.ApiFlowStep;

@ApiFlowSet
public class DinerFlows {

	@ApiFlow(name = "Menu item creation flow", 
			description = "The flow for creating a menu item. Flow begins when an authenticated user selects a diner under diners view. Then he or she sees new menu buttons. Then the user clicks on 'Aad Menu' button. User is presented with creation of menu view. After user cretes a menu then user will proceed to add new menu item to the menu.", 
			preconditions = {
			"User must be autheticated"
			},
			steps = {
				@ApiFlowStep(apimethodid = FlowConstants.USER_LOGIN_METHOD_ID),
				@ApiFlowStep(apimethodid = FlowConstants.GET_ALL_DINERS_ID),
				@ApiFlowStep(apimethodid = FlowConstants.CREATE_MENU_ID),
				@ApiFlowStep(apimethodid = FlowConstants.CREATE_MENU_ITEM_ID) }
			)
	public void bookPurchaseFlow() {

	}

}