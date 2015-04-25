package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.service.MenuCommentService;

@Controller
@RequestMapping(value="/rest/menu_comment")
public class MenuCommnetController {

	@Autowired
	private MenuCommentService menuCommentService;
	
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MenuComment> readAll() {
		return menuCommentService.findAll();
	}
	
}
