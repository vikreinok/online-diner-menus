package ee.ttu.catering.rest.controller.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ee.ttu.catering.rest.controller.dto.FileUploadForm;

public interface FileController {

	@ResponseBody
	@RequestMapping(value="/image/{fileName:.+}", method=RequestMethod.POST)
	public String createImage(String fileName, FileUploadForm form, BindingResult result);

	@RequestMapping(value="/image/{fileName:.+}", method=RequestMethod.GET,  produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
	public ResponseEntity<byte[]> getImage(String fileName);
}
