package ee.ttu.catering.rest.exception;

public class DinerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	
	public DinerNotFoundException(String dinerId) {
		super("diner not found with id: "+ dinerId);
	}

}
