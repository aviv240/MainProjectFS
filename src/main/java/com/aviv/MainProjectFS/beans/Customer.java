package com.aviv.MainProjectFS.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "Customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(length = 20, name = "FirstName")
	private String firstName;
	@Column(length = 30, name = "LastName")
	private String lastName;
	@Column(length = 15, name = "Password")
	private String password;
	@Column(unique = true, length = 50, name = "Email")
	private String email;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Customers_Coupons", joinColumns = @JoinColumn(name = "CustomerID"),inverseJoinColumns = @JoinColumn(name = "CouponID"))
	private List<Coupon> coupons;
	
//********************************************** Constructor ********************************************

	//empty Constructor for the JPA to use
	public Customer() {}
	
	/**
	 * 
	 * @param firstName - first name of the customer
	 * @param lastName - last name of the customer
	 * @param password - password of the customer
	 * @param email - email of the customer
	 */
	
	public Customer(String firstName, String lastName, String email, String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	
	}
	
	/**
	 * 
	 * @param id - id of the customer
	 * @param firstName - first name of the customer
	 * @param lastName - last name of the customer
	 * @param password - password of the customer
	 * @param email - email of the customer
	 */
	
	public Customer(int id, String firstName, String lastName, String email, String password) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	
	}

//***************************************** Getters / Setters ********************************************

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return  coupons;
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
		return "Customer [id=" + id + ", firstName=" + firstName + ", last_Name=" + lastName + ", password="
				+ password + ", email=" + email + ", coupons=" + coupons + "]";
	}
	
	@Override
	public boolean equals(Object temp) {
		if (temp instanceof Customer) {
			Customer c = (Customer) temp;
			return this.getId() == c.getId();
		}
		return false;
	}
	
}
