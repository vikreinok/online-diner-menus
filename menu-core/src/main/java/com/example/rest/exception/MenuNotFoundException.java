package com.example.rest.exception;

public class MenuNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	
	public MenuNotFoundException(String exampleId) {
		super("menu not found with id: "+ exampleId);
	}

}
