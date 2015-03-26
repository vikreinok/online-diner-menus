package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.MenuItemNotFoundException;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.repository.MenuItemRepository;
import ee.ttu.catering.rest.repository.MenuRepository;

@Service
@Transactional(rollbackFor=MenuItemNotFoundException.class)
public class MenuItemServiceImpl implements MenuItemService {
	
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MenuItemRepository menuItemRepository;

	@Override
	public MenuItem create(MenuItem menuItem) {
		Menu menu = menuRepository.findOne(menuItem.getMenu().getId());
		menuItem.setMenu(menu);
		menuItemRepository.save(menuItem);
		return menuItem;
	}
	
	@Override
	public MenuItem read(int id) {
		return menuItemRepository.findOne(id);
	}

	@Override
	public MenuItem update(MenuItem menuItem) {
		if (menuItem == null)
			throw new MenuItemNotFoundException(0);
		
		Menu menu = menuRepository.findOne(menuItem.getMenu().getId());

		menuItem.setMenu(menu);
		
		return menuItemRepository.save(menuItem);

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delete(int id) {
		MenuItem menuItem = menuItemRepository.findOne(id);
		if (menuItem == null)
			throw new MenuItemNotFoundException(id);
		menuItemRepository.delete(menuItem);
	}

	@Override
	public List<MenuItem> findAll() {
		return menuItemRepository.findAll();
	}

	@Override
	public List<MenuItem> findByMenuId(int menuId) {
		return (List<MenuItem>) menuRepository.findOne(menuId).getMenuItems();
	}

}
