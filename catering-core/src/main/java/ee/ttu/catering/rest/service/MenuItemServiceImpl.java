package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.MenuItemNotFoundException;
import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.repository.MenuItemRepository;
import ee.ttu.catering.rest.repository.MenuRepository;

@Service
@Transactional(rollbackFor=MenuNotFoundException.class)
public class MenuItemServiceImpl implements MenuItemService {
	
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;

	@Override
	public MenuItem create(int menuId, MenuItem menuItem) {
		Menu menu = menuRepository.findOne(menuId);
		menu.addMenuItem(menuItem);
		menuRepository.save(menu);
		return menuItem;
	}

	@Override
	public MenuItem  update(MenuItem menuItem) {
		if (menuItem == null || menuItem != null && menuItem.getId() == null)
			throw new MenuItemNotFoundException(menuItem.getId().toString());
		return menuItemRepository.save(menuItem);

	}

	@Override
	public MenuItem delete(Integer id) {
		MenuItem menuItem = findOne(id);
		if (menuItem == null)
			throw new MenuNotFoundException(id.toString());
		menuRepository.delete(id);
		return menuItem;
	}

	@Override
	public MenuItem findOne(Integer id) {
		return menuItemRepository.findOne(id);
	}

	@Override
	public List<MenuItem> findAll(Integer menuId) {
		return (List<MenuItem>) menuRepository.findOne(menuId).getMenuItems();
	}

}
