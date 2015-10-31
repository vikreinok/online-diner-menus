package ee.ttu.catering.rest.controller.rest;

import ee.ttu.catering.rest.controller.jsondoc.FlowConstants;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(name = "Menu service", description = "Services for managing diner menus", group = "Diner menu")
@RestController
@RequestMapping(value="/rest/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private DinerService dinerService;
	
	@ApiMethod(description="Returns all menus")
	@ApiAuthNone
	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<Menu> all() {
		return menuService.getAll();
	}
	
	@ApiMethod(description="Find menu by id")
	@ApiAuthNone
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Menu one(@ApiPathParam @PathVariable(value="id") int id) {
		return menuService.get(id);
	}
	
	@ApiMethod(id=FlowConstants.CREATE_MENU_ID, description="Menu creation service")
	@RequestMapping( method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Menu create(@RequestBody @Valid Menu menu) {
		
		int dinerId = -1;
		if(menu.getDiner() != null && menu.getDiner().getId() != null) {
			dinerId = menu.getDiner().getId();
		}
		
		menu.setDiner(null);
		Menu createdMenu = menuService.create(menu);
		
		if(dinerId != -1) {
			Diner diner = dinerService.get(dinerId);
			createdMenu.setDiner(diner);
		}
		
		return menuService.update(createdMenu);
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Menu update(@ApiPathParam @PathVariable(value="id") int id, @RequestBody @Valid Menu menu) {
		menu.setId(id);
		return menuService.update(menu);
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	@ResponseBody
	@ApiResponseObject
	public Menu delete(@ApiPathParam @PathVariable(value="id") int id) {
		return menuService.delete(id);
	}
	
	@ApiMethod(description="Adds comment to menu by menuId")
	@RequestMapping(value="/comment/{menuId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Menu addComment(@ApiPathParam @PathVariable(value="menuId") int menuId, @RequestBody @Valid MenuComment menuComment) {
		Menu menu = menuService.get(menuId);
		menu.addMenuComment(menuComment);
		
		return menuService.update(menu);
	}
	
	
	
}
