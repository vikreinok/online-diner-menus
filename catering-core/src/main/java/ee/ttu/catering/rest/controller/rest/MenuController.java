package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.response.ApiResponse;

public interface MenuController {

	@ResponseBody
	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Menu> all();

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Menu one(int id);

	@ResponseBody
	@RequestMapping( method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Menu create(Menu menu);

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse update(Integer id, Menu menu);

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Menu delete(int id);

}