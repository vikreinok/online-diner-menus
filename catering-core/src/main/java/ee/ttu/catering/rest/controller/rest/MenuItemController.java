package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.service.MenuItemService;

@Controller
@RequestMapping(value="/rest/menu_item")
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MenuItem> readAll() {
		return menuItemService.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MenuItem read(@PathVariable int id) {
		return menuItemService.read(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MenuItem create(@RequestBody @Valid MenuItem menuItem ) {
        return menuItemService.create(menuItem);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MenuItem update(@PathVariable int id, @RequestBody MenuItem menuItem) {
		menuItem.setId(id);
		return menuItemService.update(menuItem);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		 menuItemService.delete(id);
	}
	
	@RequestMapping(value="/all_menu_items/{menuId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MenuItem> readByMenu(@PathVariable int menuId) {
		return menuItemService.findByMenuId(menuId);
	}
	
}
