package com.aviv.MainProjectFS.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(unique = true, length = 50, name = "CompName") 
	private String compName;
	@Column(length = 30, name = "Password")
	private String password;
	@Column(unique = true, length = 50, name = "Email")
	private String email;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE , mappedBy = "company")
	private List<Coupon> coupons = new ArrayList<Coupon>();
	
//********************************************** Constructor ********************************************

	//empty Constructor for the JPA to use
	public Company() {};
	
	/**
	 * Constructor for creation of a new company
	 * @param compName - the name of the company
	 * @param password - the password of the company
	 * @param email - the email of the company
	 * @param coupons - ArrayList of all the coupons under the company
	 */
	
	public Company(String compName, String password, String email) {
		
		this.compName = compName;
		this.password = password;
		this.email = email;
		
	}
	
	/**
	 * Constructor to extract Companies from the Database
	 * @param id - id of the company
	 * @param compName - the name of the company
	 * @param password - the password of the company
	 * @param email - the email of the company
	 * @param coupons - ArrayList of all the coupons under the company
	 */
	
	public Company(int id, String compName, String password, String email) {
		
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
		
	}

//***************************************** Getters / Setters ********************************************

	
	public String getCompName() {
		return compName;
	}

	public void setCompName(String comp_name) {
		this.compName = comp_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	public int getId() {
		return id;
	}

//******************************************** Methods ***********************************************

	
	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}

	@Override
	public boolean equals(Object temp) {
		if (temp instanceof Company) {
			Company c = (Company) temp;
			return this.getId() == c.getId();
		}
		return false;
	}
	
	
	
	
}
