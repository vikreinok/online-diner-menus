package ee.ttu.catering.rest.controller.dto;

import javax.validation.constraints.NotNull;

import org.springframework.core.style.ToStringCreator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadForm {

	@NotNull(message = "No file uploaded")
	private CommonsMultipartFile file;

	public String toString() {
		return new ToStringCreator(this)
			.append("filename", file == null ? "No file attached" : getFilename())
			.toString();
	}
	
	public String getFilename() {
		return file.getOriginalFilename();
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
}
