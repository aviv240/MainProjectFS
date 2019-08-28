package com.aviv.MainProjectFS.facades.Exceptions;

public class UniqeKeyException extends Exception {

	public UniqeKeyException(String message) {
	
		super(message + " already exsists!");

	}
	

	
	
}
