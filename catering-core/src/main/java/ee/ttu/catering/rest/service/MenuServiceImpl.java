package ee.ttu.catering.rest.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.MenuItem;
import ee.ttu.catering.rest.repository.MenuRepository;

@Service
@Transactional(rollbackFor=MenuNotFoundException.class)
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public Menu create(Menu menu) {
		return menuRepository.save(menu);
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
		
		List<Menu> menus = menuRepository.findAll();
		Menu menu = get(id);
		if (menu == null)
			throw new MenuNotFoundException(id.toString());
		menuRepository.delete(id);
		return menu;
	}

	@Override
	public List<Menu> findDinerMenus(int dinerId) throws MenuNotFoundException {
		return menuRepository.findAll(MenuRepository.Specs.findMenusByDinerId(dinerId));
	}

	@Override
	public List<Menu> findOrganizedDinerMenus(int dinerId) throws MenuNotFoundException {
		

        Query typedQuery = em.createNativeQuery(
                "SELECT a.name, a.price, a.menu_id, a.foodType from menu_item a JOIN (select c.id from Menu c where c.diner_id=:dinerId) b ON a.menu_id = b.id ORDER BY a.menu_id, a.foodType asc"
                , Menu.class);

    
        typedQuery.setParameter("dinerId", dinerId);
		List<Menu> menus = typedQuery.getResultList();

//		List<Menu> menus = menuRepository.findAll(MenuRepository.Specs.findMenusOrganizedByDienrId(dinerId));

		for(Menu menu: menus) {

			String previousType = "";
			int index = 0;
			int addCount = 0;
			for (int i = 0; i < menu.getMenuItems().size(); i++) {
				MenuItem menuItem = menu.getMenuItems().get(i);
				
				if(!previousType.equals(menuItem.getFoodType())) {
					MenuItem newMenuItem = new MenuItem();
					newMenuItem.setName("  ");
					menu.getMenuItems().add(index + addCount + 1, newMenuItem );
					addCount++;
				}
				
				if(menuItem.getFoodType() != null ) {
					previousType = menuItem.getFoodType();
				}
			}

		}
		return menus;
	}



}
