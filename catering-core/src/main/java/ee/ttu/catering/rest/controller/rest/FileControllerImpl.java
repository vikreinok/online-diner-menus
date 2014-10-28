package ee.ttu.catering.rest.controller.rest;

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
import org.springframework.web.bind.annotation.RequestMapping;

import ee.ttu.catering.rest.controller.dto.FileUploadForm;
import ee.ttu.catering.rest.service.FileService;

@Controller
@RequestMapping(value="/file")
public class FileControllerImpl implements FileController {
	
	@Autowired
	public FileService fileService;

	@Override
	public String createImage(@PathVariable String fileName, @ModelAttribute @Valid FileUploadForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			
		}
		
		return fileService.create(form.getFilename(), form.getFile() );
	}
	
	@Override
	public ResponseEntity<byte[]> getImage(@PathVariable String pictureName) {
		
		byte[] bytes = fileService.get(pictureName);

	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);

	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
}