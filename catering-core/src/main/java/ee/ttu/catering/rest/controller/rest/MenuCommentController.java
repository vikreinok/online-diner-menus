package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.model.MenuComment;

public interface MenuCommentController {

	@ResponseBody
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuComment> readAll();
	
}