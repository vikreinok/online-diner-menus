package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.response.ApiResponse;
import ee.ttu.catering.rest.service.MenuItemService;

@Controller
@RequestMapping(value="/menu_item")
public class MenuItemControllerImpl implements MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@Override
	public List<MenuItem> readAll() {
		return menuItemService.findAll();
	}
	
	@Override
	public MenuItem readOne(@PathVariable int id) {
		return menuItemService.findOne(id);
	}
	
	@Override
	public ApiResponse create(@PathVariable Integer menuId,@RequestBody @Valid MenuItem menuItem ) {
        return new ApiResponse(HttpStatus.OK, menuItemService.create(menuId , menuItem));
	}
	
	@Override
	public ApiResponse edit(@PathVariable Integer id, @RequestBody MenuItem menuItem) {
		menuItem.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", menuItemService.update(menuItem));
	}
	
	@Override
	public MenuItem delete(@PathVariable int id) {
		return menuItemService.delete(id);
	}
	
	@Override
	public List<MenuItem> readByMenu(@PathVariable int menuId) {
		return menuItemService.findByMenuId(menuId);
	}
	
}
