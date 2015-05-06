package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.DinerNotFoundException;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.repository.DinerCommentRepository;
import ee.ttu.catering.rest.repository.DinerRepository;
import ee.ttu.catering.rest.repository.ImageRepository;

@Service
@Transactional(rollbackFor=DinerNotFoundException.class)
public class DinerServiceImpl implements DinerService {
	
	@Autowired
	private DinerRepository dinerRepository;
	@Autowired
	private DinerCommentRepository dinerCommentRepository;
	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Diner create(Diner diner) {
		return dinerRepository.save(diner);
	}

	@Override
	public Diner get(Integer id) {
		return dinerRepository.findOne(id);
	}

	@Override
	public List<Diner> getAll() {
		return dinerRepository.findAll();
	}

	@Override
	public Diner update(Diner dinerToUpdate) throws DinerNotFoundException {
		if (dinerToUpdate == null)
			throw new DinerNotFoundException(dinerToUpdate != null ? dinerToUpdate.getId().toString() : "");
		
		return dinerRepository.save(dinerToUpdate);
	}

	@Override
	public Diner delete(Integer id) throws DinerNotFoundException {
		Diner diner = get(id);
		if (diner == null)
			throw new DinerNotFoundException(id.toString());
		dinerRepository.delete(id);
		
		return diner;
	}

	@Override
	public List<Diner> findByName(String name) {
		return dinerRepository.findAll(DinerRepository.Specs.findByName(name));
	}

}
