package ee.ttu.catering.rest.repository;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.Menu_;

public interface MenuRepository extends JpaRepository<Menu, Integer>{

	public static class Specs {
		public static Specification<Menu> findByCreted(final Date created) {
			return new Specification<Menu>() {

				@Override
				public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get(Menu_.created), created);
				}

			};
		}
 		
	}
	
}
