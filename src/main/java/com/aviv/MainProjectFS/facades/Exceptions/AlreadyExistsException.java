package com.aviv.MainProjectFS.facades.Exceptions;

public class AlreadyExistsException extends Exception {

	public AlreadyExistsException(String message) {
		
		super(message + " Already exsists, please try again");
		
	}

	
	
}
