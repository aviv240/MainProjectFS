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

import com.aviv.MainProjectFS.beans.Category;
import com.aviv.MainProjectFS.beans.Company;
import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.facades.CompanyFacade;
import com.aviv.MainProjectFS.facades.Exceptions.AlreadyExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.UnChangableException;

@RestController
@RequestMapping("company")
public class CompanyController extends ClientController{

	@Autowired
	CompanyFacade compF; 
	
	@Autowired
	private Map<String, Session> tokensMap;
	
	private Session isActive(String token) {
		
		return tokensMap.get(token);
		
	}
	
	public CompanyController() {
		
		
	}



//**************************************************** Methods *******************************************************
	
//	@Override
//	@PostMapping(path = "/{email}/{password}")
//	public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password) {
//			
//		try {
//			if(compF.login(email, password)) {
//			
//				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//				
//			}
//				
//			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
//				
//		} catch (LoginFailedException e) {
//				
//			return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
//				
//		}
//	}
		
//********************************************************************************************************************
		
	@PostMapping(path = "/{token}")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @PathVariable String token){

		Session s = isActive(token);
		
		if(s != null) {
		
			try {
				
				coupon.setCompany(compF.companyDetails());
				
				compF.addCoupon(coupon);
				
				return new ResponseEntity<Coupon>(coupon, HttpStatus.CREATED);
				
			} catch (AlreadyExistsException | DoNotExistsException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
	}

//********************************************************************************************************************
	//TODO still needs checking in postman
	@PutMapping(path = "/{token}")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @PathVariable String token) {
			
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
					
				compF.updateCoupon(coupon);
					
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
					
			} catch (DoNotExistsException | UnChangableException e) {
					
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
			
	}
	
//********************************************************************************************************************

	@DeleteMapping(path = "/{couponID}/{token}")
	public ResponseEntity<?> deleteCoupon(@PathVariable("couponID") int id, @PathVariable String token) {
			
		Session s = isActive(token);
		
		if(s != null) {
			
			try {
				
				compF.deleteCoupon(id);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	
			} catch (DoNotExistsException e) {
	
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR); 
				
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
	}
	
//********************************************************************************************************************
	
	@GetMapping(path = "/coup/{token}")
	public ResponseEntity<?> getCompanyCoupon(@PathVariable String token){
		 
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
			
				return new ResponseEntity<List<Coupon>>(compF.getCompanyCoupons(), HttpStatus.OK);
				
			} catch (DoNotExistsException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
	}
	
//********************************************************************************************************************

	@GetMapping(path = "/coupCat/{category}/{token}")
	public ResponseEntity<?> getCompanyCoupon(@PathVariable("category") Category cat, @PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
			
				return new ResponseEntity<List<Coupon>>(compF.getCompanyCoupons(cat), HttpStatus.OK);
				
			} catch (DoNotExistsException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
	}
	
//********************************************************************************************************************

	@GetMapping(path = "/coupPrice/{price}/{token}")
	public ResponseEntity<?> getCompanyCoupon(@PathVariable("price") Double price, @PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
					
				return new ResponseEntity<List<Coupon>>(compF.getCompanyCoupons(price), HttpStatus.OK);
					
			} catch (DoNotExistsException e) {
					
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
					
			}
			
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
			
	}
	
//********************************************************************************************************************

	@GetMapping("/{token}")
	public ResponseEntity<?> getCompanyDetails(@PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
					
				return new ResponseEntity<Company>(compF.companyDetails(), HttpStatus.OK);
						
			} catch (DoNotExistsException e) {
						
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
						
			}
				
		}
	
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
	}
}
