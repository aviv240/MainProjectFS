package com.aviv.MainProjectFS.web;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviv.MainProjectFS.beans.Category;
import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.CustomerFacade;
import com.aviv.MainProjectFS.facades.Exceptions.CanNotPurchaseException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;

@RestController
@RequestMapping("customer")
public class CustomerController extends ClientController {
	
	@Autowired
	CustomerFacade custF;
	
	@Autowired
	private Map<String, Session> tokensMap;
	
	private Session isActive(String token) {
		
		return tokensMap.get(token);
		
	}

	public CustomerController() {
		
	}

//**************************************************** Methods *******************************************************
	
//	@Override
//	@PostMapping(path = "/{email}/{password}")
//	public ResponseEntity<?> login(@PathVariable("email") String email, @PathVariable("password") String password) {
//				
//		try {
//			if(custF.login(email, password)) {
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
//			
//********************************************************************************************************************
	
	@PostMapping(path = "/{token}")
	public ResponseEntity<?> PurchaseCoupon(@RequestBody Coupon coupon, @PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
				
				coupon = custF.getOneCoupon(coupon.getId());
				
				custF.addPurchaseCoupon(coupon);
				
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				
			} catch (DoNotExistsException | CanNotPurchaseException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
			
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
	}

//********************************************************************************************************************
	
	@GetMapping(path = "/custCoup/{token}")
	public ResponseEntity<?> getPurchasedCoupons(@PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
				
				return new ResponseEntity<List<Coupon>>(custF.getPurchasedCoupons(), HttpStatus.OK);
				
			} catch (DoNotExistsException e) {
				
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
		
	}

//********************************************************************************************************************
	
	@GetMapping(path = "/custCoupCat/{custCat}/{token}")
	public ResponseEntity<?> getPurchasedCoupons(@PathVariable("custCat") Category cat, @PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
			
				return new ResponseEntity<List<Coupon>>(custF.getPurchasedCoupons(cat), HttpStatus.OK);
			
			} catch (DoNotExistsException e) {
			
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
	
		}
	
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
	}
	
//********************************************************************************************************************
	
	@GetMapping(path = "/custCoupPrice/{custPrice}/{token}")
	public ResponseEntity<?> getPurchasedCoupons(@PathVariable("custPrice") Double price, @PathVariable String token){
		Session s = isActive(token);
		
			if(s != null) {
				
			try {
					
				return new ResponseEntity<List<Coupon>>(custF.getPurchasedCoupons(price), HttpStatus.OK);
				
			} catch (DoNotExistsException e) {
					
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
					
			}
		
		}
		
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
	}
	
//********************************************************************************************************************

	@GetMapping("/{token}")
	public ResponseEntity<?> getCustomerDetails(@PathVariable String token){
		
		Session s = isActive(token);
		
		if(s != null) {
		
			try {
					
				return new ResponseEntity<Customer>(custF.customerDetails(), HttpStatus.OK);
						
			} catch (DoNotExistsException e) {
						
				return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
						
			}
	
		}
	
		return new ResponseEntity<String>("{\"error\":\"You are not autorized to do this action!\"}", HttpStatus.UNAUTHORIZED);
	
	}
	
}
