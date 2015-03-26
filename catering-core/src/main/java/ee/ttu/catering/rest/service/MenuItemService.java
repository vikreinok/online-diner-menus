package ee.ttu.catering.rest.service;

import java.util.List;

import ee.ttu.catering.rest.model.MenuItem;

public interface MenuItemService {

	MenuItem create(MenuItem menuItem);

	MenuItem read(int id);
	
	MenuItem update(MenuItem menuItem);

	void delete(int id);

	List<MenuItem> findAll();
	
	List<MenuItem> findByMenuId(int menuId);

}
