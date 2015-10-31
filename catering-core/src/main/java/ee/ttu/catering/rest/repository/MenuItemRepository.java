package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer>{
	
}
