package ee.ttu.catering.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
