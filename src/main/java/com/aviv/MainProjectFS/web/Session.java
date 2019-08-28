package com.aviv.MainProjectFS.web;

import com.aviv.MainProjectFS.facades.ClientFacade;

public class Session {
	
	private ClientFacade facade;
	
	private long LastAccess;
	
	public ClientFacade getFacade() {
		return facade;
	}
	
	public void setFacade(ClientFacade facade) {
		this.facade = facade;
	}
	
	public long getLastAccess() {
		return LastAccess;
	}
	
	public void setLastAccess(long lastAccess) {
		LastAccess = lastAccess;
	}
	
	

}
