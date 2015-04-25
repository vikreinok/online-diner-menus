package ee.ttu.catering.rest.service;


import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private MongoDBServiceImpl mongoDbServiceImpl;
	
	@Override
	public Diner create(Diner diner) {
		
		Diner createdDiner = dinerRepository.save(diner);
		mongoDbServiceImpl.create(createdDiner);
		
		return dinerRepository.save(createdDiner);
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
		Diner updatableDiner = dinerRepository.save(dinerToUpdate);
		
//		mongoDbServiceImpl.update(updatableDiner);
		
		return updatableDiner;
	}

	@Override
	public Diner delete(Integer id) throws DinerNotFoundException {
		Diner diner = get(id);
		if (diner == null)
			throw new DinerNotFoundException(id.toString());
		dinerRepository.delete(id);
		
		mongoDbServiceImpl.delete(diner);
		
		return diner;
	}

	@Override
	public List<Diner> findByName(String name) {
		return dinerRepository.findAll(DinerRepository.Specs.findByName(name));
	}

	@Override
	public List<Diner> getAllIntegrationDiners() {
		final String urlStr = "https://api.mongolab.com/api/1/databases/catering/collections/diner/?apiKey=aPmM9PkBnI86qroYOXrBDrSkupYPVMPV";
		
		List<Diner> diners = null;
		try {
			URL url = new URL(urlStr);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			diners = objectMapper.readValue(url, new TypeReference<List<Diner>>() {});
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return diners;
	}

}
