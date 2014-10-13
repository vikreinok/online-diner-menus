package ee.ttu.catering.rest.exception;

public class ImageFileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	
	public ImageFileNotFoundException(String fileName) {
		super("fine not found with id: "+ fileName);
	}

}
