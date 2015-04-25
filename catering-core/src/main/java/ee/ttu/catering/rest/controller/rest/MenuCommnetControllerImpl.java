package ee.ttu.catering.rest.controller.rest;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.model.MenuComment;
import ee.ttu.catering.rest.service.MenuCommentService;

@Controller
@RequestMapping(value="/rest/menu_comment")
public class MenuCommnetControllerImpl implements MenuCommentController {

	@Autowired
	private MenuCommentService menuCommentService;
	
	@Override
	public List<MenuComment> readAll() {
		return menuCommentService.findAll();
	}
	
}
