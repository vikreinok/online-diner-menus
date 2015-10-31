package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.DinerComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinerCommentRepository extends JpaRepository<DinerComment, Integer>{
	
}
