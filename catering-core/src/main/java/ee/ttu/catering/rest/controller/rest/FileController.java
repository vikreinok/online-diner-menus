package ee.ttu.catering.rest.controller.rest;

import javax.validation.Valid;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthNone;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiResponseObject;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.controller.dto.FileUploadForm;
import ee.ttu.catering.rest.service.FileService;

@Api(name = "File service", description = "Services for managing image files", group = "File")
@Controller
@RequestMapping(value="/rest/file")
public class FileController {
	
	@Autowired
	public FileService fileService;

	@ApiMethod(description="Service for inputing image")
	@RequestMapping(value="/image/{fileName:.+}", method=RequestMethod.POST)
	@ResponseBody
	@ApiResponseObject
	public String createImage(@ApiPathParam @PathVariable(value="fileName") String fileName,@ApiBodyObject @ModelAttribute @Valid FileUploadForm form, BindingResult result) {
		
		if(result.hasErrors());
		
		return fileService.create(fileName, form.getFile(), form.getFilename());
	}
	
	@ApiMethod(description="Returns image data")
	@ApiAuthNone
	@ApiResponseObject
	@RequestMapping(value="/image/{fileName:.+}", method=RequestMethod.GET,  produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
	public ResponseEntity<byte[]> getImage(@ApiPathParam @PathVariable(value="fileName") String fileName) {
		
		byte[] bytes = fileService.get(fileName);

	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG);

	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
}