package com.aviv.MainProjectFS.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aviv.MainProjectFS.beans.Coupon;


@Component
public class CouponDBDAO {

	@Autowired
	CouponRepository cRepo;

	
//************************************************** Methods *********************************************************
	
	//Checks if the coupon exists in the Database
	public boolean isCouponExists(Coupon coupon) {
		
		List<Coupon> coupons = getAllCoupons();
		
		for (Coupon coupon2 : coupons) {
			
			if(coupon.getId() == coupon2.getId()) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
//*****************************************************************************************************************

	
	//Adds a coupon to the Database
	public void addCoupon(Coupon coupon) {
		
		cRepo.save(coupon);
		
	}
	
//*****************************************************************************************************************
	
	//Updates a coupon to the Database
	public void updateCoupon(Coupon coupon) {
		
		cRepo.save(coupon);
		
	}
	
//*****************************************************************************************************************

	//Deletes a coupon from the Database
	public void deleteCoupon(int id) {
		
		cRepo.deleteById(id);
		
	}
	
//*****************************************************************************************************************

	//Extracts one coupon by id from the Database
	public Coupon getOneCoupon(int id) {
		
		Optional<Coupon> coupon = cRepo.findById(id);
		
		if(coupon.isPresent()) {
			
			return coupon.get();
			
		}
		
		return null;
		
	}

//*****************************************************************************************************************
	
	//Extracts All coupons by id from the Database
	public List<Coupon> getAllCoupons(){
		
		return cRepo.findAll();
		
	}

//*****************************************************************************************************************
	
	//Decreases the amount value of the Coupon by 1
	public void addCouponPurchase(Coupon coupon) {
		
		coupon.setAmount(coupon.getAmount() - 1);
		
		updateCoupon(coupon);
		
	}

//*****************************************************************************************************************

	//Increases the amount value of the coupon by 1
	public void deleteCouponPurchase(Coupon coupon) {
		
		coupon.setAmount(coupon.getAmount() + 1);
		
		updateCoupon(coupon);
		
	}

	
}
