package com.aviv.MainProjectFS.facades;

import org.springframework.beans.factory.annotation.Autowired;

import com.aviv.MainProjectFS.db.CompanyDBDAO;
import com.aviv.MainProjectFS.db.CouponDBDAO;
import com.aviv.MainProjectFS.db.CustomerDBDAO;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;

public abstract class ClientFacade {
	
	@Autowired
	protected CouponDBDAO coupDBDAO;
	
	@Autowired
	protected CompanyDBDAO compDBDAO;
	
	@Autowired
	protected CustomerDBDAO custDBDAO;
	
	
	public boolean login(String email, String password) throws LoginFailedException, DoNotExistsException {
		
		return false;
		
	}
		
		
		
	
}
