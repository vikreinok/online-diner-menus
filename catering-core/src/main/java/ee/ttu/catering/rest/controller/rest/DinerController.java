package ee.ttu.catering.rest.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ee.ttu.catering.rest.controller.dto.FileUploadForm;
import ee.ttu.catering.rest.model.Diner;
import ee.ttu.catering.rest.response.ApiResponse;
import ee.ttu.catering.rest.service.DinerService;
import ee.ttu.catering.rest.service.FileService;

@Controller
@RequestMapping(value="/diner")
public class DinerController extends AbstractRestController{

	@Autowired
	private DinerService dinerService;
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value="", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Diner> all() {
		return dinerService.getAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Diner one(@PathVariable int id) {
		return dinerService.get(id);
	}
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Diner> one(@PathVariable String name) {
		return dinerService.findByName(name);
	}
	
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse create(@RequestBody @Valid Diner menu) {
            return new ApiResponse(HttpStatus.OK, dinerService.create(menu));
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public ApiResponse edit(@PathVariable Integer id, @RequestBody Diner menu) {
		menu.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", dinerService.update(menu));
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT )
	public void delete(@PathVariable int id) {
		dinerService.delete(id);
	}
	
	
	@RequestMapping(value="/picture/upload/{fileName}", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@PathVariable String fileName,
           @ModelAttribute @Valid FileUploadForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			
		}
		
		return fileService.create(form.getFilename(), form.getFile() );
	}
	
	@RequestMapping(value="/picture/{pictureName}", method=RequestMethod.GET,  produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> getFile(@PathVariable String pictureName) {
		
		byte[] bytes = fileService.get(pictureName);

	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);

	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
	
}
