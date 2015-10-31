package ee.ttu.catering.rest.service;

import ee.ttu.catering.rest.exception.DinerNotFoundException;
import ee.ttu.catering.rest.model.Diner;

import java.util.List;

public interface DinerService {

	public Diner create(Diner menu);
	public Diner get(Integer id);
	public List<Diner> getAll();
	public Diner update(Diner sp) throws DinerNotFoundException;
	public Diner delete(Integer id) throws DinerNotFoundException;
	public List<Diner> findByName(String name);
	
}
