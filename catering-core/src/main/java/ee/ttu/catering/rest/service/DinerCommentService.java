package ee.ttu.catering.rest.service;

import java.util.List;

import ee.ttu.catering.rest.model.DinerComment;

public interface DinerCommentService {
	
	List<DinerComment> findAll();
	
	DinerComment save(DinerComment dinerComment);
}
