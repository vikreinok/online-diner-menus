package ee.ttu.catering.rest.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.Diner_;

public interface DinerRepository extends JpaRepository<Diner, Integer>, JpaSpecificationExecutor<Diner>{

	public static class Specs {
		public static Specification<Diner> findByName(final String name) {
			return new Specification<Diner>() {

				@Override
				public Predicate toPredicate(Root<Diner> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.like(root.get(Diner_.name), name + "%");
				}

			};
		}
 		
	}
	
	
}
