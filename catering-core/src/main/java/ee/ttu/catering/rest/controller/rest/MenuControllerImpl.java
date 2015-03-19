package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;

@Controller
@RequestMapping(value="/menu")
public class MenuControllerImpl implements MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private DinerService dinerService;
	
	@Override
	public List<Menu> all() {
		return menuService.getAll();
	}
	
	@Override
	public Menu one(@PathVariable int id) {
		return menuService.get(id);
	}
	
	@Override
	public Menu create(@RequestBody @Valid Menu menu) {
		
		int dinerId = -1;
		if(menu.getDiner() != null && menu.getDiner().getId() != null) {
			dinerId = menu.getDiner().getId();
		}
		
		menu.setDiner(null);
		Menu createdMenu = menuService.create(menu);
		
		if(dinerId != -1) {
			Diner diner = dinerService.get(dinerId);
			createdMenu.setDiner(diner);
		}
		
		return menuService.update(createdMenu);
	}
	
	@Override
	public Menu update(@PathVariable int id, @RequestBody @Valid Menu menu) {
		menu.setId(id);
		return menuService.update(menu);
	}
	
	@Override
	public Menu delete(@PathVariable int id) {
		return menuService.delete(id);
	}
	
	@Override
	public Menu addComment(@PathVariable int menuId, @RequestBody @Valid MenuComment menuComment) {
		Menu menu = menuService.get(menuId);
		menu.addMenuComment(menuComment);
		
		return menuService.update(menu);
	}
	
	
	
}
