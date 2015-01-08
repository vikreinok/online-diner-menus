package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.response.ApiResponse;
import ee.ttu.catering.rest.service.DinerService;

@Controller
@RequestMapping(value="/diner")
public class DinerControllerImpl implements DinerController {

	@Autowired
	private DinerService dinerService;

	@Override
	public List<Diner> all() {
		return dinerService.getAll();
	}
	
	@Override
	public Diner one(@PathVariable int id) {
		return dinerService.get(id);
	}
	
	@Override
	public Diner create(@Valid @RequestBody Diner diner) {
		return dinerService.create(diner);
	}
	
	@Override
	public ApiResponse edit(@PathVariable Integer id, @RequestBody Diner diner) {
		diner.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", dinerService.update(diner));
	}
	
	@Override
	public void delete(@PathVariable Integer id) {
		dinerService.delete(id);
	}
	
	@Override
	public List<Diner> findByName(@PathVariable String name) {
		return dinerService.findByName(name);
	}
	
	
}
