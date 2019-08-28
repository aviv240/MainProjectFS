package com.aviv.MainProjectFS.db;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;

@Component
public class CustomerDBDAO {

	@Autowired
	CustomerRepository cRepo;
	
	
//*************************************************** Methods ******************************************************
	
	//Checks if the company exists in the Database 
	public boolean isCustomerExists(String email, String password) {
		
		ArrayList<Customer> customers = (ArrayList<Customer>) cRepo.findAll();
		
		for (Customer customer : customers) {
			
			if(customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
//*****************************************************************************************************************

	//Adds a customer to the Database
	public void addCustomer(Customer customer) {
			
		cRepo.save(customer);
		
	}
	
//*****************************************************************************************************************

	//Updates a customer in the Database
	public void updateCustomer(Customer customer) {
				
		
		cRepo.save(customer);
		
	}
	
//*****************************************************************************************************************
	
	//Deletes a Customer from the Database
	public void deleteCustomer(int id) {
		
		cRepo.deleteById(id);
		
	}
	
//*****************************************************************************************************************
	
	//Extracts one customer by id from the Database
	public Customer getOneCustomer(int id) throws DoNotExistsException {
		
		Optional<Customer> customer = cRepo.findById(id);
		
		if(customer.isPresent()) {
			
			return customer.get();
			
		}else {
			
			throw new DoNotExistsException("Customer");
			
		}
		
		
		
	}
	
//*****************************************************************************************************************
	
	//Extracts all the customers from the Database
	public ArrayList<Customer> getAllCustomers() {
		
		return (ArrayList<Customer>) cRepo.findAll();
		
	}
	
//*****************************************************************************************************************

	//extracts the id for the customer from the Database
	public int idExtractor(String email) {
		
		return cRepo.getCustomersByEmail(email).getId();
		
	}
	

	


















}
