package com.aviv.MainProjectFS.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviv.MainProjectFS.beans.Company;
import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.AdminFacade;
import com.aviv.MainProjectFS.facades.Exceptions.AlreadyExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.UnChangableException;
import com.aviv.MainProjectFS.facades.Exceptions.UniqeKeyException;

@RestController
@RequestMapping("admin")
public class AdminController extends ClientController {

	@Autowired
	AdminFacade adminF;
	
	@Autowired
	private Map<String, Session> tokensMap;
	
	private Session isActive(String token) {
		
		return tokensMap.get(token);
		
	}
	
	public AdminController() {
		
		
		
	}

//**************************************************** Methods *******************************************************
	
//TODO - create a page to do a global login like on facebook.
	
//	@Override
//	@PostMapping(path = "/{email}/{password}/{token}")
//	public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
//		
//		try {
//			if(adminF.login(email, password)) {
//			
//				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//			
//			}
//			
//			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
//			
//		} catch (LoginFailedException e) {
//			
//			return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}" , HttpStatus.INTERNAL_SERVER_ERROR);
//			
//		}
//	}
	
//********************************************************************************************************************
	
	@PostMapping(path = "/compAdd/{token}")
	public ResponseEntity<?> addCompany(@RequestBody Company company, @PathVariable String token) {
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
				
				s.setLastAccess(System.currentTimeMillis());
				
				adminF.addCompany(company);
				
				return new ResponseEntity<Company>(company, HttpStatus.CREATED);
				
			} catch (AlreadyExistsException | UniqeKeyException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
	}
	
	
//********************************************************************************************************************
	
	@PutMapping(path = "/compUpd/{token}")
	public ResponseEntity<?> updateCompany(@RequestBody Company company, @PathVariable String token) {
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
				
				s.setLastAccess(System.currentTimeMillis());
				
				adminF.updateCompany(company);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				
			} catch (DoNotExistsException | UnChangableException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
		
	}
	
//********************************************************************************************************************
	
	@DeleteMapping(path = "/compDel/{companyID}/{token}")
	public ResponseEntity<?> deleteCompany(@PathVariable("companyID") int id, @PathVariable String token) {
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {

				s.setLastAccess(System.currentTimeMillis());
				
				adminF.deleteCompany(id);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	
			} catch (DoNotExistsException e) {
	
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR); 
				
			}
			
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
		
	}
	
//********************************************************************************************************************

	@GetMapping(path = "/comp/{companyID}/{token}")
	public ResponseEntity<?> getOneCompany(@PathVariable("companyID") int id, @PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
		try {

			s.setLastAccess(System.currentTimeMillis());
			
			return new ResponseEntity<Company>(adminF.getOneCompany(id), HttpStatus.OK);
			
		} catch (DoNotExistsException e) {
			
			return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
		
	}
	
//********************************************************************************************************************

	@GetMapping(path = "/compAll/{token}")
	public ResponseEntity<?> getAllCompanies(@PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			s.setLastAccess(System.currentTimeMillis());
			
			return new ResponseEntity<List<Company>>(adminF.getAllCompanies(), HttpStatus.OK);
		
		}
	
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);

	}
//********************************************************************************************************************
	
	@PostMapping(path = "/custAdd/{token}")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, @PathVariable String token) {
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {

				s.setLastAccess(System.currentTimeMillis());
				
				adminF.addCustomer(customer);
				
				return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
				
			} catch (AlreadyExistsException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);

		
	}
	
//********************************************************************************************************************
	
	@PutMapping(path = "/custUpd/{token}")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable String token) {
			
		Session s = isActive(token);
		
		if(s != null) {
			
			try {

				s.setLastAccess(System.currentTimeMillis());
				
				adminF.updateCustomer(customer);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					
			} catch (DoNotExistsException | UnChangableException e) {
					
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
					
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);

			
	}
		
//********************************************************************************************************************
		
	@DeleteMapping(path = "/custDel/{customerID}/{token}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("customerID") int id, @PathVariable String token) {
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {

				s.setLastAccess(System.currentTimeMillis());
				
				adminF.deleteCustomer(id);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	
			} catch (DoNotExistsException e) {
	
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR); 
				
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);

		
	}
	
//********************************************************************************************************************

	@GetMapping(path = "/cust/{customerID}/{token}")
	public ResponseEntity<?> getOneCustomer(@PathVariable("customerID") int id, @PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {

				s.setLastAccess(System.currentTimeMillis());
				
				return new ResponseEntity<Customer>(adminF.getOneCustomer(id), HttpStatus.OK);
					
			} catch (DoNotExistsException e) {
					
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
					
			}
			
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);

			
	}
		
//********************************************************************************************************************

	@GetMapping(path = "/custAll/{token}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {

			s.setLastAccess(System.currentTimeMillis());
			
			return new ResponseEntity<List<Customer>>(adminF.getAllCustomers(), HttpStatus.OK);
	
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);

			
	}
	
}
