package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import javax.validation.Valid;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.controller.jsondoc.FlowConstants;
import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.service.MenuItemService;

@Api(name = "MenuItem service", description = "Services for managing diner menu itmes", group = "Diner menu")
@Controller
@RequestMapping(value="/rest/menu_item")
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<MenuItem> readAll() {
		return menuItemService.findAll();
	}
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public MenuItem read(@ApiPathParam @PathVariable(value="id")  int id) {
		return menuItemService.read(id);
	}
	
	@ApiMethod(id=FlowConstants.CREATE_MENU_ITEM_ID)
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public MenuItem create(@RequestBody @Valid MenuItem menuItem ) {
        return menuItemService.create(menuItem);
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public MenuItem update(@ApiPathParam @PathVariable(value="id") int id, @RequestBody MenuItem menuItem) {
		menuItem.setId(id);
		return menuItemService.update(menuItem);
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@ApiPathParam @PathVariable(value="id") int id) {
		 menuItemService.delete(id);
	}
	
	@ApiMethod(description="Find all menuitems realted to menu by menuId")
	@ApiAuthNone
	@RequestMapping(value="/all_menu_items/{menuId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<MenuItem> readByMenu(@PathVariable int menuId) {
		return menuItemService.findByMenuId(menuId);
	}
	
}
