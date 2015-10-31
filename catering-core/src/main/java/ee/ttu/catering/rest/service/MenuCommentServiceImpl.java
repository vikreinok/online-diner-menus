package ee.ttu.catering.rest.service;


import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.repository.MenuCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
