package ee.ttu.catering.rest.service;

import ee.ttu.catering.rest.model.DinerComment;

import java.util.List;

public interface DinerCommentService {
	
	List<DinerComment> findAll();
	
	DinerComment save(DinerComment dinerComment);
}
