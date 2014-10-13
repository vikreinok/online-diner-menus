package ee.ttu.catering.rest.exception;

public class MenuNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	
	public MenuNotFoundException(String menuId) {
		super("menu not found with id: "+ menuId);
	}

}
