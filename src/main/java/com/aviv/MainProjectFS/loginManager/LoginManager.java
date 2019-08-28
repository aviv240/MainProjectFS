package com.aviv.MainProjectFS.loginManager;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.aviv.MainProjectFS.facades.AdminFacade;
import com.aviv.MainProjectFS.facades.ClientFacade;
import com.aviv.MainProjectFS.facades.CompanyFacade;
import com.aviv.MainProjectFS.facades.CustomerFacade;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;

@Component
//@Scope(Singleton)
public class LoginManager {

	@Autowired
	private ApplicationContext context;
	
	/**
	 * Login method which initializes the correct Object(depends on the ClientType) and returns it
	 * 
	 * @param email - email for login
	 * @param password - password for login
	 * @param client - which type of client (Administrator, Company, Customer)
	 * @param context - ConfigurableApplicationContext class
	 * @return
	 * @throws LoginFailedException - if authentication of email and password fails 
	 * @throws DoNotExistsException - if the Client doesn't exist
	 */
	public ClientFacade login(String email, String password, ClientType client) throws LoginFailedException, DoNotExistsException{
		
		switch(client) {
		
			case Administrator:
				
				AdminFacade admin = context.getBean(AdminFacade.class);
				
				if(admin.login(email, password)) {
					
					System.out.println("Login was successfull");
					
					return admin;
					
				}
				
				
				
				
			case Company:
				
				CompanyFacade company = context.getBean(CompanyFacade.class);
				
				if(company.login(email, password)) {
					
					System.out.println("Login was successfull");
					
					return company;
					
				}
				
			
			case Customer:
				
				CustomerFacade customer = context.getBean(CustomerFacade.class);
				
				if(customer.login(email, password)) {
					
					System.out.println("Login was successfull");
					
					return customer;
				
				}
				
				
				
		default:
			
			throw new LoginFailedException();
				
		}
		
		
		
		
	}
	
}
