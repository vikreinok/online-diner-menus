package ee.ttu.catering.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.DinerComment;

public interface DinerCommentRepository extends JpaRepository<DinerComment, Integer>{
	
}
