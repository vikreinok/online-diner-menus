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
	
	@ResponseBody
	@RequestMapping(value="/create", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse create(@RequestBody @Valid Diner menu) {
            return new ApiResponse(HttpStatus.OK, dinerService.create(menu));
	}
	
	@ResponseBody
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ApiResponse edit(@PathVariable Integer id, @RequestBody Diner menu) {
		menu.setId(id);
		return new ApiResponse(HttpStatus.OK, "ok", dinerService.update(menu));
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public Diner deleteSmartphone(@PathVariable int id) {
		return dinerService.delete(id);
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Diner> all() {
		return dinerService.getAll();
	}
	
	@RequestMapping(value="/read/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Diner one(@PathVariable int id) {
		return dinerService.get(id);
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
	    headers.setContentType(MediaType.IMAGE_PNG);

	    return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);
	}
	
//	@POST
//	@Path("/upload/{filename}")	
//	@Consumes("multipart/form-data")
//	public Response uploadFile(	@PathParam("filename") String filename, MultipartFormDataInput input) {
// 
//		String file = "";
//		
//		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
//		List<InputPart> inputParts = uploadForm.get("file");
// 
//		for (InputPart inputPart : inputParts) {
// 
//		 try {
// 
//			MultivaluedMap<String, String> header = inputPart.getHeaders();
//			
//			//String ext = FilenameUtils.getExtension(getFileName(header));
//		
//			//convert the uploaded file to inputstream
//			InputStream inputStream = inputPart.getBody(InputStream.class,null);
// 
//			byte [] bytes = IOUtils.toByteArray(inputStream);
// 			
//			file = Consts.BASE_PATH_FILE + File.separator + filename;
// 
//			writeFile(bytes,file);
//  
//		  } catch (IOException e) {
//			e.printStackTrace();
//		  }
// 
//		} 
//		return Response.status(200).entity("uploadFile is called, Uploaded file name : " + file).build(); 
//	}
	
	
	
}
