package com.example.rest.controller.rest;


import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.rest.model.Menu;
import com.example.rest.response.ApiResponse;
import com.example.rest.service.MenuService;

@Controller
@Path("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ApiResponse create(@RequestBody @Valid Menu menu) {
            
            return new ApiResponse(HttpStatus.OK, menuService.create(menu));
	}
	
	@PUT
	@Path("/edit/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public ApiResponse edit(@PathVariable Integer id, @RequestBody Menu menu) {
		menu.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", menuService.update(menu));
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	public Menu deleteSmartphone(@PathVariable int id) {
		return menuService.delete(id);
	}
	
	
	@RequestMapping(value="", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Menu> all() {
		return menuService.getAll();
	}
	
}
