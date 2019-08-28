package com.aviv.MainProjectFS.thread;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.db.CouponDBDAO;
import com.aviv.MainProjectFS.facades.CompanyFacade;
import com.aviv.MainProjectFS.facades.CustomerFacade;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;

@Component
//@Scope("singleton")
public class DailyThread implements Runnable{
	
	@Autowired
	private CompanyFacade compF;
	
	@Autowired
	private CouponDBDAO coupDBDAO;
	
	private static boolean quit = false;

	
//********************************************** Constructor ********************************************

	public DailyThread() {
	
	}

//******************************************** Methods ***********************************************

	//Thread that checks if the endDate on the coupons is expired (current time is later than the endDate time)
	@Override
	public void run() {
		
		while(!quit) {
			
			Calendar cal = Calendar.getInstance();
			
			ArrayList<Coupon> coupons =  (ArrayList<Coupon>) coupDBDAO.getAllCoupons();
			
			for (Coupon coupon : coupons) {
			
				if(coupon.getEndDate().after(cal.getTime())) {
					
					try {
						
						compF.deleteCoupon(coupon.getId());
						
					} catch (DoNotExistsException e) {
						
						System.out.println(e.getMessage());
						
					}
					
				}
				
			}
			
			try {
			
				Thread.sleep(1000 * 60);//test purposes
				//Thread.sleep(1000 * 60 * 60 * 12);
			} catch (InterruptedException e) {
				
				System.out.println(e.getMessage());
			}
			
		}
		
		
	}

//*******************************************************************************************************************
	
	//changes the boolean of this class so the thread will stop running
	public void quit() {
		
		quit = true;
		
	}
	
}
