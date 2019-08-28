package com.aviv.MainProjectFS.facades.Exceptions;

public class UnChangableException extends Exception {

	
	public UnChangableException() {
		
		super(" You changed something you shouldn't have changed, please check your input again ");
		
	}

	
	
}
