package ee.ttu.catering.rest.service;

import java.util.List;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.Menu;

public interface MenuService {

	public Menu create(Menu menu);
	public Menu get(Integer id);
	public List<Menu> getAll();
	public Menu update(Menu sp) throws MenuNotFoundException;
	public Menu delete(Integer id) throws MenuNotFoundException;
}
