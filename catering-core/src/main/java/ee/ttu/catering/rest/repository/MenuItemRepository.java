package ee.ttu.catering.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
	
}
