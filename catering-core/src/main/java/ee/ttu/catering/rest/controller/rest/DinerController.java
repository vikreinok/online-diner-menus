package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ee.ttu.catering.rest.controller.jsondoc.FlowConstants;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.DinerComment;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.service.DinerCommentService;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;

@Api(name = "Diner service", description = "Services for managing diners", group = "Diner")
@RestController
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

	@ApiMethod(id=FlowConstants.GET_ALL_DINERS_ID)
	@ApiAuthNone
	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Diner> all() {
		return dinerService.getAll();
	}
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Diner one(@PathVariable int id) {
		return dinerService.get(id);
	}
	
	@ApiMethod
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Diner create(@Valid @RequestBody Diner diner) {
		return dinerService.create(diner);
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Diner update(@PathVariable Integer id, @RequestBody Diner diner) {
		diner.setId(id);
		return dinerService.update(diner) ;
	}
	
	@ApiMethod
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	public void delete(@PathVariable Integer id) {
		dinerService.delete(id);
	}
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(value="/menus/{dinerId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Menu> findDinerMenus(@PathVariable Integer dinerId) {
		return menuService.findDinerMenus(dinerId);
	}
	
	@ApiMethod
	@ApiAuthNone
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Diner> findByName(@PathVariable String name) {
		return dinerService.findByName(name);
	}
	
	@ApiMethod
	@RequestMapping(value="/comment/{dinerId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Transactional
	public Diner addComment(@PathVariable int dinerId, @RequestBody @Valid DinerComment dinerComment) {
		
		dinerComment = dinerCommentService.save(dinerComment);

		Diner diner = dinerService.get(dinerId);
		
		diner.addDinerComment(dinerComment);
		
//		entityManager.persist(dinerComment);
//		entityManager.createNativeQuery("INSERT INTO diner_diner_comment (dinerComments_id, diner_id) VALUES ("+ dinerComment.getId() +","+ dinerId +")").executeUpdate();
//		entityManager.flush();
		
		return dinerService.update(diner);
	}

	
}
