package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.response.ApiResponse;

public interface DinerController {

	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Diner> all();

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Diner one(int id);
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Diner> findByName(String name);

	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse create(Diner menu);

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse edit(Integer id, Diner menu);

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	public void delete(Integer id);

}