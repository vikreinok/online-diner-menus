package ee.ttu.catering.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.Diner;

public interface DinerRepository extends JpaRepository<Diner, Integer>{

}
