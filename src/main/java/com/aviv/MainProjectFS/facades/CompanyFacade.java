package com.aviv.MainProjectFS.facades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aviv.MainProjectFS.beans.Category;
import com.aviv.MainProjectFS.beans.Company;
import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.Exceptions.AlreadyExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;
import com.aviv.MainProjectFS.facades.Exceptions.UnChangableException;

@Service
public class CompanyFacade extends ClientFacade{

	private int companyID;
	
	public CompanyFacade() {
		
		
	}
	
//************************************************** Methods *********************************************************

	//Checking email and password to allow use of the facade
	public boolean login(String email, String password) throws LoginFailedException {
		
		if(compDBDAO.isCompanyExists(email, password)) {
			
			companyID = compDBDAO.idExtractor(email);
			
			return true;
			
		}
		
		throw new LoginFailedException();
		
	}
	
//*****************************************************************************************************************
	
	//Adds a Coupon to the Database, after checking if the Coupon's title exists within the specific Company
	public void addCoupon(Coupon coupon) throws AlreadyExistsException, DoNotExistsException {
		
		Company comp = companyDetails();
		
		//coupon.setCompany(comp);
		
		List<Coupon> coupons = comp.getCoupons();
		
		for (Coupon couponTemp : coupons) {
			
			if(coupon.getTitle().equals(couponTemp.getTitle())) {
				
				throw new AlreadyExistsException("Coupon");
				
			}
			
		}
		
		coupDBDAO.addCoupon(coupon);

		System.out.println("Added coupon successfully");
		
	}
	
//*****************************************************************************************************************

	//Updates a Coupon in the Database, after checking the Coupon exists,
	//and making sure the new coupon id and Company password are the same as before
	public void updateCoupon(Coupon coupon) throws UnChangableException, DoNotExistsException {
		
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) coupDBDAO.getAllCoupons();
		
		if(coupDBDAO.isCouponExists(coupon)) {
			
			coupon.setCompany(companyDetails());
			
			for (Coupon coupon2 : coupons) {
				
				if(coupon.getCompany().getId() == coupon2.getCompany().getId() && coupon.getId() == coupon2.getId()) {
					
					coupDBDAO.updateCoupon(coupon);
					
					System.out.println("Updated coupon successfully");
					
				}
				else {
					
					throw new UnChangableException();
					
				}
				
			}
			
		}
		else {
				
			throw new DoNotExistsException("Coupon");
		
		}
		
	}
	
//*****************************************************************************************************************
	
	//Deletes Coupon from the Database, and deletes the foreign keys in the Customer_coupons table
	public void deleteCoupon(int id) throws DoNotExistsException {
		
		Coupon coupon = coupDBDAO.getOneCoupon(id);
		
		if(coupDBDAO.isCouponExists(coupon)) {
			
			List<Customer> customers = custDBDAO.getAllCustomers();
			
			for (Customer customer : customers) {
				
				customer.getCoupons().remove(coupon);
//				List<Coupon> couponsTemp = new ArrayList<Coupon>();
//				
//				List<Coupon> coupons = customer.getCoupons();
//	 			
//				for (Coupon coupon2 : coupons) {
//					
//					if(!coupon.equals(coupon2)) {
//						
//						couponsTemp.add(coupon2);
//						
//					}
//					
//				}
				
//				customer.setCoupons(couponsTemp);
				custDBDAO.updateCustomer(customer);
				
			}
			
			coupon.setCompany(null);
			coupDBDAO.updateCoupon(coupon);
			coupDBDAO.deleteCoupon(coupon.getId());
			
			System.out.println("Deleted coupon successfully");
			
		}else {
			
			throw new DoNotExistsException("Coupon");
			
		}
		
	}
	
//*****************************************************************************************************************

	//Return all the Company Coupons
	public List<Coupon> getCompanyCoupons() throws DoNotExistsException {
		
		List<Coupon> coupons = compDBDAO.getOneCompany(companyID).getCoupons();
		
		return coupons;
			
		
		
		
		
	}
	
//*****************************************************************************************************************
	
	//Returns all the Company Coupons with a specific Category
	public List<Coupon> getCompanyCoupons(Category cat) throws DoNotExistsException{
		
		List<Coupon> couponsTemp =  getCompanyCoupons();
		
		List<Coupon> coupons = new ArrayList<>();
		
		for (Coupon coupon : couponsTemp) {
			
			if((coupon.getCategory().equals(cat))) {
				
				coupons.add(coupon);
				
			}
			
		}
		
		return coupons;
		
	}

//*****************************************************************************************************************
	
	//Returns all Company Coupons below a specific price
	public List<Coupon> getCompanyCoupons(double maxPrice) throws DoNotExistsException {
		
		List<Coupon> couponsTemp =  getCompanyCoupons();
		
		List<Coupon> coupons = new ArrayList<>();
		
		for (Coupon coupon : couponsTemp) {
			
			if(coupon.getPrice() <= maxPrice) {
				
				coupons.add(coupon);
				
			}
			
		}
		
		return coupons;
		
		
	}
	
//*****************************************************************************************************************

	//Prints the company details
	public Company companyDetails() throws DoNotExistsException {
		
		return compDBDAO.getOneCompany(companyID);
		
	}
}
