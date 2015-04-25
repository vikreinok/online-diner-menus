package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.service.MenuItemService;

@Controller
@RequestMapping(value="/rest/menu_item")
public class MenuItemControllerImpl implements MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@Override
	public List<MenuItem> readAll() {
		return menuItemService.findAll();
	}
	
	@Override
	public MenuItem read(@PathVariable int id) {
		return menuItemService.read(id);
	}
	
	@Override
	public MenuItem create(@RequestBody @Valid MenuItem menuItem ) {
        return menuItemService.create(menuItem);
	}
	
	@Override
	public MenuItem update(@PathVariable int id, @RequestBody MenuItem menuItem) {
		menuItem.setId(id);
		return menuItemService.update(menuItem);
	}
	
	@Override
	public void delete(@PathVariable int id) {
		 menuItemService.delete(id);
	}
	
	@Override
	public List<MenuItem> readByMenu(@PathVariable int menuId) {
		return menuItemService.findByMenuId(menuId);
	}
	
}
