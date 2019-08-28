package com.aviv.MainProjectFS.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aviv.MainProjectFS.beans.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

	public Company getCompaniesByEmail(String email);
	
}
