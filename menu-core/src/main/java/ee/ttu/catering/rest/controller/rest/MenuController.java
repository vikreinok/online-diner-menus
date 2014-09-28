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

import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.response.ApiResponse;
import ee.ttu.catering.rest.service.MenuService;

@Controller
@RequestMapping(value="/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
//	@POST
//	@Path("/create")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse create(@RequestBody @Valid Menu menu) {
            
            return new ApiResponse(HttpStatus.OK, menuService.create(menu));
	}
	
//	@PUT
//	@Path("/edit/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@ResponseBody
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse edit(@PathVariable Integer id, @RequestBody Menu menu) {
		menu.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", menuService.update(menu));
	}
	
//	@DELETE
//	@Path("/delete/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
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
