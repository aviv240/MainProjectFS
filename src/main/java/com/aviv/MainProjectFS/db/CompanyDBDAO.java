package com.aviv.MainProjectFS.db;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aviv.MainProjectFS.beans.Company;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;


@Component
public class CompanyDBDAO {

	@Autowired
	CompanyRepository cRepo;
	
	
	
//*************************************************** Methods ******************************************************

	//Checks if the company exists in the Database
	public boolean isCompanyExists(String email, String password) {
		
		ArrayList<Company> companies = (ArrayList<Company>) cRepo.findAll();
		
		for (Company company : companies) {
			
			if(company.getEmail().equals(email) && company.getPassword().equals(password)) {
				
				
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
//*****************************************************************************************************************
	
	//Adds a company to the Database
	public void addCompany(Company company) {
		cRepo.save(company);
		
	}
	
//*****************************************************************************************************************

	//Updates a company in the Database
	public void updateCompany(Company company) {
		if(cRepo.existsById(company.getId()))
			cRepo.save(company);
				
	}
	
//*****************************************************************************************************************

	//Deletes a Company from the Database
	public void deleteCompany(int id) {
		
		cRepo.deleteById(id);
				
	}

//*****************************************************************************************************************

	//Extracts one company by id from the Database
	public Company getOneCompany(int id) throws DoNotExistsException {
		
		Optional<Company> company = cRepo.findById(id);
		
		if(company.isPresent()) {
			
			return company.get();
			
		}else {
			
			throw new DoNotExistsException("Company");
			
		}
		
		
		
	}
	
//*****************************************************************************************************************

	//Extracts all the companies from the Database
	public ArrayList<Company> getAllCompanies() {
		
		return (ArrayList<Company>) cRepo.findAll();
		
	}
	
//*****************************************************************************************************************

	//extracts the id for the company from the Database
	public int idExtractor(String email) {
		
		return  cRepo.getCompaniesByEmail(email).getId();
		
	}
	
	
	
	
}
