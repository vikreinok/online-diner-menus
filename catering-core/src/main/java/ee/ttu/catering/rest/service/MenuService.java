package ee.ttu.catering.rest.service;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.Menu;

import java.util.List;

public interface MenuService {

	public Menu create(Menu menu);
	public Menu get(Integer id);
	public List<Menu> getAll();
	public Menu update(Menu sp) throws MenuNotFoundException;
	public Menu delete(Integer id) throws MenuNotFoundException;
	public List<Menu> findDinerMenus(int dinerId) throws MenuNotFoundException;
	
}
