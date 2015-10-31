package ee.ttu.catering.rest.repository;

import ee.ttu.catering.rest.model.MenuComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCommentRepository extends JpaRepository<MenuComment, Integer>{
	
}
