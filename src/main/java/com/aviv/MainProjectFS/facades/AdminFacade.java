package com.aviv.MainProjectFS.facades;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aviv.MainProjectFS.beans.Company;
import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.Exceptions.AlreadyExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;
import com.aviv.MainProjectFS.facades.Exceptions.UnChangableException;
import com.aviv.MainProjectFS.facades.Exceptions.UniqeKeyException;

@Service
public class AdminFacade extends ClientFacade{
	
	public AdminFacade() {
		
		
	}

//******************************************** Company Methods ***********************************************

	//Checking email and password to allow use of the facade
	public boolean login(String email, String password) throws LoginFailedException {
		
		if(email.equals("admin@admin.com") && password.equals("admin")) {
			
			return true;
			
		}
		
 		throw new LoginFailedException();
		
	}
	
//*****************************************************************************************************************

	//Adds a Company to the Database, after checking if the Company exists
	public void addCompany(Company company) throws AlreadyExistsException, UniqeKeyException {
	
		if(compDBDAO.isCompanyExists(company.getEmail(), company.getPassword())) {
			
			throw new AlreadyExistsException("Company");
			
		}
		else {
			
			ArrayList<Company> companies = getAllCompanies();
			
			for (Company company2 : companies) {
				
				if(company.getCompName().equals(company2.getCompName())) {
					
					throw new UniqeKeyException("Company Name");
					
				}
				
			}
			compDBDAO.addCompany(company);
			
			System.out.println("Company has been added successfully");
			
		}
		
	}

//*****************************************************************************************************************
	
	//Updates a Company in the Database, after checking the Company exists,
	//and making sure the new password and name are the same as before
	public void updateCompany(Company company) throws DoNotExistsException, UnChangableException {
		
		Company compTemp = compDBDAO.getOneCompany(company.getId());
			
		System.out.println(compTemp);
			
		if(compTemp.getCompName().equals(company.getCompName()) && compTemp.getId() == company.getId()) {
				
			compDBDAO.updateCompany(company);
				
			System.out.println("Company has been updated successfully");
				
		}
		else {
				
			throw new UnChangableException();
				
		}
		
	}

//*****************************************************************************************************************

	//Deletes Company from the Database and the coupons under it, and deletes the foreign keys in the Customer_coupons table
	public void deleteCompany(int id) throws DoNotExistsException {
		
		Company company = getOneCompany(id);
		
		if(compDBDAO.isCompanyExists(company.getEmail(), company.getPassword())) {
		
			List<Coupon> coupons = getCompanyCoupons(company.getId());
			
			for (Coupon coupon : coupons) {
					
				deleteCoupon(coupon);
				
			}
			
			compDBDAO.deleteCompany(id);
			
			System.out.println("Company has been deleted successfully");
		
		}else {
			
			throw new DoNotExistsException("Company");
			
		}
		
}
	
//*****************************************************************************************************************
	
	//Returns a specific company
	public Company getOneCompany(int id) throws DoNotExistsException {
		
		return compDBDAO.getOneCompany(id);
	
	}

//*****************************************************************************************************************

	//Returns an array of all the Company that exist in the Database
	public ArrayList<Company> getAllCompanies(){
		
		return compDBDAO.getAllCompanies();
		
	}
	
//******************************************** Customer Methods ***********************************************
	
	//Adds a Customer to the Database, after checking if the Customer exists
	public void addCustomer(Customer customer) throws AlreadyExistsException {
			
			if(custDBDAO.isCustomerExists(customer.getEmail(), customer.getPassword())) {
				
				throw new AlreadyExistsException("Customer");
				
			}
			else {
				
				
				custDBDAO.addCustomer(customer);
				
				System.out.println("Customer has been added successfully");
				
			}
			
	}

//*****************************************************************************************************************

	//Updates a Customer in the Database, after checking the Customer exists,
	//and making sure the new password is the same as before
	public void updateCustomer(Customer customer) throws UnChangableException, DoNotExistsException {
					
			Customer custTemp = custDBDAO.getOneCustomer(customer.getId());
						
			if(custTemp.getId() == customer.getId()) {
				
				custDBDAO.updateCustomer(customer);
				
				System.out.println("Customer has been updated successfully");
				
			}
			else {
				
				throw new UnChangableException();
				
			}
					
	}
		
	

//*****************************************************************************************************************

	//Deletes Customer from the Database and the coupons under it, and deletes the foreign keys in the Customer_coupons table
	public void deleteCustomer(int id) throws DoNotExistsException {
		
		Customer customer = getOneCustomer(id);
		
		if(custDBDAO.isCustomerExists(customer.getEmail(), customer.getPassword())) {
			
			deleteCouponPurchase(customer);
			
			custDBDAO.deleteCustomer(customer.getId());
			
			System.out.println("Customer has been deleted");	
		
		}else {
			
			throw new DoNotExistsException("Customer");
			
		}
	}
	
//*****************************************************************************************************************

	//Returns a specific Customer
	public Customer getOneCustomer(int id) throws DoNotExistsException {
		
		return custDBDAO.getOneCustomer(id);
		
	}
	
//*****************************************************************************************************************
	
	//Returns an array of all the Customer that exist in the Database
	public ArrayList<Customer> getAllCustomers(){
		
		return custDBDAO.getAllCustomers();
		
	}
	
//*****************************************************************************************************************
	
	public void deleteCouponPurchase(Customer customer) {
		
		List<Coupon> coupons = new ArrayList<Coupon>();
			
		customer.setCoupons(coupons);
		custDBDAO.updateCustomer(customer);
		
	}
	
//*****************************************************************************************************************

	public void deleteCoupon(Coupon coupon) throws DoNotExistsException {
		
		if(coupDBDAO.isCouponExists(coupon)) {
			
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
		public List<Coupon> getCompanyCoupons(int companyID) throws DoNotExistsException {
			
			List<Coupon> coupons = compDBDAO.getOneCompany(companyID).getCoupons();
			
			return coupons;
		
		}
	
}
