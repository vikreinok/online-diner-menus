package ee.ttu.catering.rest.model.base;

import java.util.ArrayList;
import java.util.List;
 
public class ValidationErrorDTO {
 
    private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>(2);
 
    public ValidationErrorDTO() {
    }
 
    public void addFieldError(String path, String message) {
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
 
    
}