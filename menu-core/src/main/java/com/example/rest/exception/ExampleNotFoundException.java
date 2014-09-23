package com.example.rest.exception;

public class ExampleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2859292084648724403L;
	
	public ExampleNotFoundException(String exampleId) {
		super("example not found with id: "+exampleId);
	}

}
