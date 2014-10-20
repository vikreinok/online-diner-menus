package ee.ttu.catering.rest.service;

import java.util.List;

import ee.ttu.catering.rest.model.MenuItem;

public interface MenuItemService {

	MenuItem create(int menuId, MenuItem menuItem);

	MenuItem update(MenuItem menuItem);

	MenuItem delete(Integer id);

	MenuItem findOne(Integer id);

	List<MenuItem> findAll(Integer menuId);

}
