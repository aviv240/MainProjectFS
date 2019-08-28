package com.aviv.MainProjectFS.facades.Exceptions;

public class DoNotExistsException extends Exception {

	public DoNotExistsException(String message) {
		
		super(message + " Doesn't exists, please try again");
		
	}

	
	
}
