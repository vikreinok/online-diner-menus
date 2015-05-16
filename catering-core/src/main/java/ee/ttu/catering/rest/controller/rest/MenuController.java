package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ee.ttu.catering.rest.controller.jsondoc.FlowConstants;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;

@Api(name = "Menu service", description = "Services for managing diner menus", group = "Diner menu")
@RestController
@RequestMapping(value="/rest/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private DinerService dinerService;
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Menu> all() {
		return menuService.getAll();
	}
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Menu one(@PathVariable int id) {
		return menuService.get(id);
	}
	
	@ApiMethod(id=FlowConstants.CREATE_MENU_ID)
	@RequestMapping( method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
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
	public Menu update(@PathVariable int id, @RequestBody @Valid Menu menu) {
		menu.setId(id);
		return menuService.update(menu);
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	@ResponseBody
	public Menu delete(@PathVariable int id) {
		return menuService.delete(id);
	}
	
	@ApiMethod
	@RequestMapping(value="/comment/{menuId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Menu addComment(@PathVariable int menuId, @RequestBody @Valid MenuComment menuComment) {
		Menu menu = menuService.get(menuId);
		menu.addMenuComment(menuComment);
		
		return menuService.update(menu);
	}
	
	
	
}
