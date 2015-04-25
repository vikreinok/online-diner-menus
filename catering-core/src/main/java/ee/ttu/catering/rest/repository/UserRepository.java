package ee.ttu.catering.rest.repository;

import org.springframework.data.repository.CrudRepository;

import ee.ttu.catering.rest.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);

}
