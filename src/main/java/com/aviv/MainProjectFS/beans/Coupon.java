package com.aviv.MainProjectFS.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Coupons")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "Amount")
	private int amount;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Company")
	@JsonIgnore
	private Company company;

	@Column(name = "Price")
	private double price;

	@Column(length = 500, name = "Image")
	private String image;
	@Column(length = 200, unique = true, name = "Title")
	private String title;
	@Column(length = 500, name = "Description")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "Category")
	private Category category;

	@Column(name = "StartDate")
	private Date startDate;
	@Column(name = "EndDate")
	private Date endDate;

//********************************************** Constructor ********************************************

	// empty Constructor for the JPA to use
	public Coupon() {
	};

	/**
	 * 
	 * @param company_id - id of the connected(foreign key) company id
	 * @param category   - the category of the coupon
	 * @param title      - the title of the coupon
	 * @param startDate  - the date the coupon have been created
	 * @param endDate    - the expiration date of the coupon
	 * @param amount     - the amount of coupons able for purchase
	 * @param message    - the description of the coupon
	 * @param price      - the price of the coupon
	 * @param image      - the link of the image of the coupon
	 */

	public Coupon(int amount, Company company, double price, String image, String title,
			String description, Category category, Date startDate, Date endDate) {

		this.amount = amount;
		this.company = company;
		this.price = price;
		this.image = image;
		this.title = title;
		this.description = description;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;

	}

	/**
	 * 
	 * @param id         - id of the coupon
	 * @param company_id - id of the connected(foreign key) company id
	 * @param category   - the category of the coupon
	 * @param title      - the title of the coupon
	 * @param startDate  - the date the coupon have been created
	 * @param endDate    - the expiration date of the coupon
	 * @param amount     - the amount of coupons able for purchase
	 * @param message    - the description of the coupon
	 * @param price      - the price of the coupon
	 * @param image      - the link of the image of the coupon
	 */

	public Coupon(int id, int amount, Company company, double price, String image, String title,
			String description, Category category, Date startDate, Date endDate) {

		this.id = id;
		this.amount = amount;
		this.company = company;
		this.price = price;
		this.image = image;
		this.title = title;
		this.description = description;
		this.category = category;
		this.startDate = startDate;
		this.endDate = endDate;

	}

//***************************************** Getters / Setters ********************************************

	public double getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

//******************************************** Methods ***********************************************

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", amount=" + amount + ", price=" + price + ", image="
				+ image + ", title=" + title + ", description=" + description + ", category="
				+ category + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Coupon) {
			Coupon c = (Coupon) arg0;
			return this.getId() == c.getId();
		}
		return false;
	}

}
