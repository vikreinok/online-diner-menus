package ee.ttu.catering.rest.controller.rest;

import ee.ttu.catering.rest.controller.jsondoc.FlowConstants;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.model.DinerComment;
import ee.ttu.catering.rest.model.Menu;
import ee.ttu.catering.rest.service.DinerCommentService;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.MenuService;
import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


	@ApiMethod(id=FlowConstants.GET_ALL_DINERS_ID, description="Returns all diners")
	@ApiAuthNone
	@RequestMapping(value="", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<Diner> all() {
		return dinerService.getAll();
	}

	@ApiMethod(description="Diner by id")
	@ApiAuthNone
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Diner one(@ApiPathParam @PathVariable(value="id")  int id) {
		return dinerService.get(id);
	}

	@ApiMethod(description="Create diner")
	@RequestMapping(method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Diner create(@Valid @ApiBodyObject @RequestBody Diner diner) {
		return dinerService.create(diner);
	}

	@ApiMethod(description="Diner by id")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Diner update(@ApiPathParam @PathVariable(value="id") Integer id, @RequestBody Diner diner) {
		diner.setId(id);
		return dinerService.update(diner) ;
	}

	@ApiMethod(description="Delete diner by id")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	public void delete(@ApiPathParam @PathVariable(value="id")  Integer id) {
		dinerService.delete(id);
	}

	@ApiMethod(description="Find menus for by diner dinerId")
	@ApiAuthNone
	@RequestMapping(value="/menus/{dinerId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<Menu> findDinerMenus(@ApiPathParam @PathVariable(value="dinerId") Integer dinerId) {
		return menuService.findDinerMenus(dinerId);
	}

	@ApiMethod(description="Search diner by name")
	@ApiAuthNone
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public List<Diner> findByName(@ApiPathParam @PathVariable(value="name") String name) {
		return dinerService.findByName(name);
	}

	@ApiMethod(description="Add comment to dner by dienrId")
	@RequestMapping(value="/comment/{dinerId}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiResponseObject
	public Diner addComment(@ApiPathParam @PathVariable(value="dinerId") int dinerId, @ApiBodyObject @RequestBody @Valid DinerComment dinerComment) {

		dinerComment = dinerCommentService.save(dinerComment);

		Diner diner = dinerService.get(dinerId);

		diner.addDinerComment(dinerComment);

//		entityManager.persist(dinerComment);
//		entityManager.createNativeQuery("INSERT INTO diner_diner_comment (dinerComments_id, diner_id) VALUES ("+ dinerComment.getId() +","+ dinerId +")").executeUpdate();
//		entityManager.flush();

		return dinerService.update(diner);
	}


}
