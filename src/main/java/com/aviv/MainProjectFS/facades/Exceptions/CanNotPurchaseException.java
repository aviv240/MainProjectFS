package com.aviv.MainProjectFS.facades.Exceptions;

public class CanNotPurchaseException extends Exception {

	public CanNotPurchaseException() {
		
		super("Coupon cannot be purchased, please try again later");
		
	}

	
	
}
