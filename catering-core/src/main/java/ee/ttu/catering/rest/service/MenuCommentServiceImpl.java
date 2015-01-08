package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.repository.MenuCommentRepository;

@Service
@Transactional(rollbackFor=MenuNotFoundException.class)
public class MenuCommentServiceImpl implements MenuCommentService {
	
	@Autowired
	private MenuCommentRepository menuCommentRepository;
	 
	@Override
	public List<MenuComment> findAll() {
		return menuCommentRepository.findAll();
	}

}
