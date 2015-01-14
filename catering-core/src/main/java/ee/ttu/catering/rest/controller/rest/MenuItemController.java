package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.response.ApiResponse;

public interface MenuItemController {

	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuItem> readAll();
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MenuItem readOne(int id);
	
	@ResponseBody
	@RequestMapping(value="/{menuId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse create(Integer menuId, MenuItem menuItem);
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse edit(Integer id, MenuItem menuItem);
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MenuItem delete(int id);
	
	@ResponseBody
	@RequestMapping(value="/by_menu_id/{menuId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuItem> readByMenu(int menuId);

}