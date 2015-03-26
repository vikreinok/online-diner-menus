package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.MenuItem;

public interface MenuItemController {

	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuItem> readAll();
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MenuItem read(int id);
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MenuItem create(MenuItem menuItem);
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public MenuItem update(int id, MenuItem menuItem);
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(int id);

	@ResponseBody
	@RequestMapping(value="/all_menu_items/{menuId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<MenuItem> readByMenu(int menuId);
	
}