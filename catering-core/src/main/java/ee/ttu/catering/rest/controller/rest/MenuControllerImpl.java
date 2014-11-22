package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.response.ApiResponse;
import ee.ttu.catering.rest.service.MenuService;

@Controller
@RequestMapping(value="/menu")
public class MenuControllerImpl implements MenuController {

	@Autowired
	private MenuService menuService;
	
	@Override
	public List<Menu> all() {
		return menuService.getAll();
	}
	
	@Override
	public Menu one(@PathVariable int id) {
		return menuService.get(id);
	}
	
	@Override
	public ApiResponse create(@RequestBody @Valid Menu menu) {
		return new ApiResponse(HttpStatus.OK, menuService.create(menu));
	}
	
	@Override
	public ApiResponse update(@PathVariable Integer id, @RequestBody Menu menu) {
		menu.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", menuService.update(menu));
	}
	
	@Override
	public Menu delete(@PathVariable int id) {
		return menuService.delete(id);
	}
	
	
	
}
