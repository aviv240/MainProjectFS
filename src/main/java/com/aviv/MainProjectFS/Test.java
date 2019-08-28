package com.aviv.MainProjectFS;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.aviv.MainProjectFS.beans.Category;
import com.aviv.MainProjectFS.beans.Company;
import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.beans.Customer;
import com.aviv.MainProjectFS.facades.AdminFacade;
import com.aviv.MainProjectFS.facades.CompanyFacade;
import com.aviv.MainProjectFS.facades.CustomerFacade;
import com.aviv.MainProjectFS.facades.Exceptions.AlreadyExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.CanNotPurchaseException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;
import com.aviv.MainProjectFS.facades.Exceptions.UnChangableException;
import com.aviv.MainProjectFS.facades.Exceptions.UniqeKeyException;
import com.aviv.MainProjectFS.loginManager.ClientType;
import com.aviv.MainProjectFS.loginManager.LoginManager;
import com.aviv.MainProjectFS.thread.DailyThread;

@Component
//@Scope("singleton")
public class Test {

	private ConfigurableApplicationContext context;

//************************************************* Constructor *****************************************************

	//Constructor with a context variable to use with facades
	public Test(ConfigurableApplicationContext context) {
	
		this.context = context;
		
	}
	
//*************************************************** Methods *******************************************************
	
	//main program
	public void purposePreview() throws LoginFailedException, AlreadyExistsException, DoNotExistsException, UnChangableException, UniqeKeyException, CanNotPurchaseException {
		
		//starting the thread
		DailyThread dt = context.getBean(DailyThread.class);
		
		Thread t = new Thread(dt);
		
		t.start();
		
		Scanner scan = new Scanner(System.in);
		
		LoginManager lm = new LoginManager();
	
		System.out.println("Please enter you email");
		
		String email = "dd20@gmail.com";//scan.next();
		
		System.out.println("Please enter your password");
		
		String password = "525252";//scan.next();
		
		System.out.println("What client are you? (Customer - 1, Company - 2, Administrator - 3)");
		
		int choice = scan.nextInt();
		
		//Login in with the email password and the ClientType
		switch(choice) {
		
//************************************************ Customer commands **************************************************			

		case 1:
			
			CustomerFacade custF = (CustomerFacade) lm.login(email, password, ClientType.Customer);
//			
			//extracts a specific coupon from the Database
//			Coupon coupon = custF.getOneCoupon(12);
//			Coupon coupon2 = custF.getOneCoupon(11);
//			
			//Adds a new coupon purchase to the joinTable of customers and coupons
//			custF.addPurchaseCoupon(coupon);
//			custF.addPurchaseCoupon(coupon2);
//			
			//Prints the getPurchasedCoupons methods
//			System.out.println(custF.getPurchasedCoupons());
//			System.out.println(custF.getPurchasedCoupons(Category.food));
//			System.out.println(custF.getPurchasedCoupons(80));
//			
			//deletes a coupon purchase from the Database
//			custF.deleteCouponPurchase(coupon);
			
			
			
			break;
			
//*********************************************** Company commands **************************************************			
			
		case 2:
			
			CompanyFacade compF = (CompanyFacade) lm.login(email, password, ClientType.Company);
//			
			//Defining Coupons
//			Date date = new Date(Calendar.getInstance().getTimeInMillis());
//			
//			Calendar cal = Calendar.getInstance();
//			
//			cal.set(Calendar.YEAR, 2020);
//			
//			Date date2 = new Date(cal.getTimeInMillis());
//			
//			Coupon coupon = new Coupon(5, compF.companyDetails(), 15.0, "url_link", "This is a coupon for spa", "Good Spa", "coupon to get a relaxing spa treatment for just 15 bucks", Category.spa, date, date2);
//		
//			Coupon coupon2 = new Coupon(5, compF.companyDetails(), 45.0, "url_link", "This is a coupon for spa++", "Good Spa++", "just like the other but better", Category.spa, date, date2);
//
//			Coupon coupon3 = new Coupon(5, compF.companyDetails(), 95.0, "url_link", "This is a coupon for food", "Good Food", "coupon to get a yummy cake", Category.food, date, date2);
//
//			//Adding the coupons to the Database under the current company that logged in
//			compF.addCoupon(coupon);
//			compF.addCoupon(coupon2);
//			
			//Delete a specific coupon
//			compF.deleteCoupon(coupon);
			
			//Changing and updating the coupon
//			coupon.setAmount(6);
//			compF.updateCoupon(coupon);
//			
			//Gets the company details and prints it
//			System.out.println(compF.companyDetails());
//			
			//Prints the getCompanyCoupons methods
//			System.out.println(compF.getCompanyCoupons());
//			System.out.println(compF.getCompanyCoupons(Category.spa));
//			System.out.println(compF.getCompanyCoupons(40.0));
			
			break;
			
			
		case 3:
			
			AdminFacade adminF = (AdminFacade) lm.login(email, password, ClientType.Administrator);
			
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();

//******************************************** Admin commands:Company ***********************************************			
			
			//Defining companies
//			Company comp = new Company("Deluca Heights", "123456", "deluca@heights.com");
//			Company comp2 = new Company("Deluca Heights", "1234567", "deluca@height.com");
//
			//Adding companies to the Database
//			adminF.addCompany(comp);
//			adminF.addCompany(comp2);
//			
//			Company comp = adminF.getOneCompany(1);
//			
			//Change and update the company
//			comp.setEmail("Deluca@height.com");
//			adminF.updateCompany(comp);
//			
			//Gets all companies in the Database
//			ArrayList<Company> companies = adminF.getAllCompanies();
//			
			//Delete a specific Company
//			adminF.deleteCompany(comp);
//
//			
//******************************************** Admin commands:Customer ***********************************************			
//
			//Defines customers
//			Customer cust = new Customer("Moshe", "Ehsom", "me20@gmail.com", "252525");
//			Customer cust2 = new Customer("David", "Divad", "dd20@gmail.com", "525252");
//						
			//Adding customers to the Database
//			adminF.addCustomer(cust);
//			adminF.addCustomer(cust2);
//			
//			Gets a specific Customer from the Database
//			Customer cust = adminF.getOneCustomer(3);
//			
			//Change and update the Customer
//			cust.setFirstName("Eliyahu");
//			adminF.updateCustomer(cust2);
//			
			//Delete a specific Customer
//			adminF.deleteCustomer(cust);
//			
//			System.out.println(adminF.getlAllCustomers());
			
			break;
			
			
	
		}
	
		//Makes the thread stop in the next interval
		dt.quit();
		
	}
	
}
