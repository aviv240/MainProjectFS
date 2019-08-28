package com.aviv.MainProjectFS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.aviv.MainProjectFS.beans.Coupon;
import com.aviv.MainProjectFS.facades.Exceptions.AlreadyExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.CanNotPurchaseException;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;
import com.aviv.MainProjectFS.facades.Exceptions.UnChangableException;
import com.aviv.MainProjectFS.facades.Exceptions.UniqeKeyException;

@SpringBootApplication
public class MainProjectJpaApplication {

	public static void main(String[] args) throws LoginFailedException, AlreadyExistsException, DoNotExistsException, UnChangableException, UniqeKeyException, CanNotPurchaseException {
		ConfigurableApplicationContext context = SpringApplication.run(MainProjectJpaApplication.class);
		
		//Initialization of test
//		Test test = new Test(context);
//		
//		//Using test method to run the program
//		test.purposePreview();
		
	}
	
}
