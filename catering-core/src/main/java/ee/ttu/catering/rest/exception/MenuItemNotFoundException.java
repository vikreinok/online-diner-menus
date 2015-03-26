package ee.ttu.catering.rest.exception;

public class MenuItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724433L;
	
	public MenuItemNotFoundException(int menuItemId) {
		super("MenuItem not found with id: " + menuItemId);
	}

}
