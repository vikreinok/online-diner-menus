package ee.ttu.catering.rest.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = "/rest/status")
	public void restStatus(HttpServletResponse httpServletResponse) {
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("index");
	}

}
