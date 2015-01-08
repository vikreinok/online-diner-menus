package ee.ttu.catering.rest.service;

import java.util.List;

import ee.ttu.catering.rest.model.MenuComment;

public interface MenuCommentService {
	
	List<MenuComment> findAll();
	
}
