package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.Token;
import ee.ttu.catering.rest.model.Token_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

public interface TokenRepository extends JpaRepository<Token, Long>, JpaSpecificationExecutor<Token> {

    //    public Token value(String value);

    public static class Specs {
        public static Specification<Token> value(final String value) {
            return new Specification<Token>() {

                @Override
                public Predicate toPredicate(Root<Token> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.equal(root.get(Token_.value), value);
                }
            };
        }


//        @Modifying
//        @Query("DELETE FROM Token t WHERE t.lastAccess <= :instant")
//        public void deleteOlderThan(@Param("instant") Date instant);

        public static Specification<Token> olderThan(final Date date) {
            return new Specification<Token>() {

                @Override
                public Predicate toPredicate(Root<Token> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    return cb.lessThanOrEqualTo(root.get(Token_.lastAccess), date);
                }
            };
        }
    }

}
