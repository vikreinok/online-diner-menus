package ee.ttu.catering.rest.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@Api(name = "Inedex service", description = "Services for pinging", group = "Index")
public class IndexController {

	@ApiMethod(description="Return status of application")
	@RequestMapping(value = "/rest/status")
	public void restStatus(HttpServletResponse httpServletResponse) {
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	}
	
	@RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("index");
	}

}
