package ee.ttu.catering.rest.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.Diner_;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.model.Menu_;

public interface MenuRepository extends JpaRepository<Menu, Integer>, JpaSpecificationExecutor<Menu>{
	
	public static class Specs {
		public static Specification<Menu> findMenusByDienrId(final int dinerId) {
			return new Specification<Menu>() {

				@Override
	            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	                Join<Menu, Diner> diners = root.join(Menu_.diner);
	                return cb.equal(diners.get(Diner_.id), dinerId);
	            }
			};
		}
	}
	  
	
}
