package ee.ttu.catering.rest.service;

import ee.ttu.catering.rest.model.MenuComment;

import java.util.List;

public interface MenuCommentService {
	
	List<MenuComment> findAll();
	
}
