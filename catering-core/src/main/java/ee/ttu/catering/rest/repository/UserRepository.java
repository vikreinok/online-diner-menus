package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);

}
