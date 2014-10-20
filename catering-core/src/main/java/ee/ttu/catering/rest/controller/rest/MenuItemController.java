package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.response.ApiResponse;
import ee.ttu.catering.rest.service.MenuItemService;

@Controller
@RequestMapping(value="/menu_item")
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;
	
	@ResponseBody
	@RequestMapping(value="/create/{menuId}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse create(@PathVariable Integer menuId,@RequestBody @Valid MenuItem menuItem ) {
            
            return new ApiResponse(HttpStatus.OK, menuItemService.create(menuId , menuItem));
	}
	
	@ResponseBody
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse edit(@PathVariable Integer id, @RequestBody MenuItem menuItem) {
		menuItem.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", menuItemService.update(menuItem));
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public MenuItem delete(@PathVariable int id) {
		return menuItemService.delete(id);
	}
	
	@RequestMapping(value="/read_one/{id}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public MenuItem readOne(@PathVariable int id) {
		return menuItemService.findOne(id);
	}
	
	@RequestMapping(value="/read_all/{menuId}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<MenuItem> readAll(@PathVariable int menuId) {
		return menuItemService.findAll(menuId);
	}
	
}
