package ee.ttu.catering.rest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ee.ttu.catering.rest.exception.DinerNotFoundException;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.repository.DinerRepository;

@Service
@Transactional(rollbackFor=DinerNotFoundException.class)
public class DinerServiceImpl implements DinerService {
	
	@Autowired
	private DinerRepository dinerRepository;

	@Override
	public Diner create(Diner sp) {
		return dinerRepository.save(sp);
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
		dinerRepository.save(dinerToUpdate);
		return dinerToUpdate;
	}

	@Override
	public Diner delete(Integer id) throws DinerNotFoundException {
		Diner Diner = get(id);
		if (Diner == null)
			throw new DinerNotFoundException(id.toString());
		dinerRepository.delete(id);
		return Diner;
	}

	@Override
	public List<Diner> findByName(String name) {
		return dinerRepository.findAll(DinerRepository.Specs.findByName(name));
	}

}
