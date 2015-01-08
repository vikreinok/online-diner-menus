package ee.ttu.catering.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ee.ttu.catering.rest.model.MenuComment;

public interface MenuCommentRepository extends JpaRepository<MenuComment, Integer>{
	
}
