package com.aviv.MainProjectFS.facades;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aviv.MainProjectFS.beans.Category;
import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.Exceptions.CanNotPurchaseException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;

@Service
public class CustomerFacade extends ClientFacade{

	private int customerID;

	public CustomerFacade() {
	
	}
	
//************************************************** Methods *********************************************************

	//Checking email and password to allow use of the facade
	public boolean login(String email, String password) throws LoginFailedException{
		
		if(custDBDAO.isCustomerExists(email, password)) {
			
			customerID = custDBDAO.idExtractor(email);
			
			return true;
			
		} else {
			
			throw new LoginFailedException();
			
		}
		
		
	}
	
//*****************************************************************************************************************

	//Validates and adds coupon purchase to the customer's array of coupons and updates the customer so it will affect the Database
	public void addPurchaseCoupon(Coupon coupon) throws CanNotPurchaseException, DoNotExistsException {
		
		boolean couponExistForCustomer = false;
			
		Customer customer = custDBDAO.getOneCustomer(customerID);
		
		List<Coupon> coupons = customer.getCoupons();
		
		for (Coupon coupon2 : coupons) {
			
			if(coupon.getTitle().equals(coupon2.getTitle())) {
				
				couponExistForCustomer = true;
				
			}
			
		}
	
		Calendar cal = Calendar.getInstance();
		
		if(coupon.getAmount() >= 0 && couponExistForCustomer == false && coupon.getEndDate().after(new Date(cal.getTimeInMillis()))) {
		
			customer.getCoupons().add(coupon);
			
			coupDBDAO.addCouponPurchase(coupon);
			
			custDBDAO.updateCustomer(customer);
			
			System.out.println("Coupon purchased successfully");
		
		} 
		else {
			
			throw new CanNotPurchaseException();
			
		}
		
	}
	
//*******************************************************************************************************************

	public void deleteCouponPurchase(Coupon coupon) {
		
		if (coupDBDAO.isCouponExists(coupon)) {
		
		List<Customer> customers = custDBDAO.getAllCustomers();
		
		for (Customer customer : customers) {
			
			List<Coupon> couponsTemp = new ArrayList<Coupon>();
			
			List<Coupon> coupons = customer.getCoupons();
 			
			for (Coupon coupon2 : coupons) {
				
				if(!coupon.equals(coupon2)) {
					
					couponsTemp.add(coupon2);
					
				}
				
			}
			
			customer.setCoupons(couponsTemp);
			custDBDAO.updateCustomer(customer);
			
			coupon.setAmount(coupon.getAmount() + 1);
			coupDBDAO.updateCoupon(coupon);
			
		}
		
		}
	}
	
//*******************************************************************************************************************

	public List<Coupon> getPurchasedCoupons() throws DoNotExistsException {
		
		Customer customer = custDBDAO.getOneCustomer(customerID);
		
		return customer.getCoupons();
		
	}
	
//*******************************************************************************************************************
	public List<Coupon> getPurchasedCoupons(Category cat) throws DoNotExistsException {
	
		List<Coupon> couponsTemp =  getPurchasedCoupons();
		
		List<Coupon> coupons = new ArrayList<>();
		
		for (Coupon coupon : couponsTemp) {
			
			if((coupon.getCategory().equals(cat))) {
				
				coupons.add(coupon);
				
			}
			
		}
		
		return coupons;
		
	}
	
//*******************************************************************************************************************
	
	public List<Coupon> getPurchasedCoupons(double maxPrice) throws DoNotExistsException {
		
		List<Coupon> couponsTemp =  getPurchasedCoupons();
		
		List<Coupon> coupons = new ArrayList<>();
			
		for (Coupon coupon : couponsTemp) {
				
			if(coupon.getPrice() <= maxPrice) {
					
				coupons.add(coupon);
					
			}
				
		}
			
		return coupons;
			
	}
	
//*******************************************************************************************************************

	public Customer customerDetails() throws DoNotExistsException {
		
		return custDBDAO.getOneCustomer(customerID);
		
	}

//*******************************************************************************************************************

	public Coupon getOneCoupon(int id) {
		
		return coupDBDAO.getOneCoupon(id);
		
	}
	
//*****************************************************************************************************************

	public List<Coupon> getAllCoupons(){
		
		return coupDBDAO.getAllCoupons();
		
	}
	
}
