package com.aviv.MainProjectFS.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aviv.MainProjectFS.beans.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{
	

}
