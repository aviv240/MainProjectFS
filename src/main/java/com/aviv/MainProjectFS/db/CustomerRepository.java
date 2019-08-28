package com.aviv.MainProjectFS.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aviv.MainProjectFS.beans.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	public Customer getCustomersByEmail(String email);
	
	
}
