package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.DinerComment;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.service.DinerCommentService;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;

@Controller
@RequestMapping(value="/rest/diner")
public class DinerController {

	@Autowired
	private DinerService dinerService;
	@Autowired
	private DinerCommentService dinerCommentService;
	@Autowired
	private MenuService menuService;
	
	@PersistenceContext( )
    private EntityManager entityManager;

	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Diner> all() {
		return dinerService.getAll();
	}
	
	@RequestMapping(value="/integration/", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Diner> allIntegration() {
		return dinerService.getAllIntegrationDiners();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Diner one(@PathVariable int id) {
		return dinerService.get(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Diner create(@Valid @RequestBody Diner diner) {
		return dinerService.create(diner);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Diner update(@PathVariable Integer id, @RequestBody Diner diner) {
		diner.setId(id);
		return dinerService.update(diner) ;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	public void delete(@PathVariable Integer id) {
		dinerService.delete(id);
	}
	
	@RequestMapping(value="/menus/{dinerId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Menu> findDinerMenus(@PathVariable Integer dinerId) {
		return menuService.findDinerMenus(dinerId);
	}
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Diner> findByName(@PathVariable String name) {
		return dinerService.findByName(name);
	}
	
	@RequestMapping(value="/comment/{dinerId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional(value=TxType.REQUIRES_NEW)
	public Diner addComment(@PathVariable int dinerId, @RequestBody @Valid DinerComment dinerComment) {
		
		Diner diner = dinerService.get(dinerId);
		dinerComment.setDiner(diner);
		dinerComment = dinerCommentService.save(dinerComment);
		
		diner.addDinerComment(dinerComment);
		
//		entityManager.persist(dinerComment);
//		
//		Diner diner = entityManager.find(Diner.class, dinerId);
//		diner.addDinerComment(dinerComment);
//		entityManager.merge(diner);
//		entityManager.flush();
		
		return dinerService.update(diner);
	}

	
}
