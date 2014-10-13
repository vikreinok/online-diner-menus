package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.repository.MenuRepository;

@Service
@Transactional(rollbackFor=MenuNotFoundException.class)
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu create(Menu sp) {
		return menuRepository.save(sp);
	}

	@Override
	public Menu get(Integer id) {
		return menuRepository.findOne(id);
	}

	@Override
	public List<Menu> getAll() {
		return menuRepository.findAll();
	}

	@Override
	public Menu update(Menu menuToUpdate) throws MenuNotFoundException {
		if (menuToUpdate == null)
			throw new MenuNotFoundException(menuToUpdate.getId().toString());
		menuRepository.save(menuToUpdate);
		return menuToUpdate;
	}

	@Override
	public Menu delete(Integer id) throws MenuNotFoundException {
		Menu Menu = get(id);
		if (Menu == null)
			throw new MenuNotFoundException(id.toString());
		menuRepository.delete(id);
		return Menu;
	}

}
