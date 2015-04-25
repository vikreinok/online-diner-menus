package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.MenuNotFoundException;
import ee.ttu.catering.rest.model.DinerComment;
import ee.ttu.catering.rest.repository.DinerCommentRepository;

@Service
@Transactional(rollbackFor=MenuNotFoundException.class)
public class DinerCommentServiceImpl implements DinerCommentService {
	
	@Autowired
	private DinerCommentRepository dinerCommentRepository;
	 
	@Override
	public List<DinerComment> findAll() {
		return dinerCommentRepository.findAll();
	}

	@Override
	public DinerComment save(DinerComment dinerComment) {
		return dinerCommentRepository.save(dinerComment);
	}

}
