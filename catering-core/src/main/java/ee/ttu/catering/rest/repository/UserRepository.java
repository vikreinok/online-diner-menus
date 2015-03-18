package ee.ttu.catering.rest.repository;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ee.ttu.catering.rest.model.User;
import ee.ttu.catering.rest.model.User_;

/**
 * Interface for the Data Access Object for the User model. It extends JpaRepository which is part of Spring Data JPA and declares all the commons
 * methods.
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * This query will be automatically implemented by it's name, "findBy" is the key work and "Login" is parsed as the criteria. By parsing the
	 * parameters declared, the login match the "Login" from "findBy".
	 *
	 * @param login instance to be the value of the criteria
	 * @return a single user matching the login
	 */
	User findByLogin(String login);
  
	public static class Specs {
		public static Specification<User> findUser(final String username, final String password) {
			return new Specification<User>() {

				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.and(
							cb.equal(root.get(User_.login), username ),
							cb.equal(root.get(User_.password), password)
							);
				}

			};
		}

	}

}
