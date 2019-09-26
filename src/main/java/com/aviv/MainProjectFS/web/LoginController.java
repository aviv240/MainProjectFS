package com.aviv.MainProjectFS.web;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviv.MainProjectFS.facades.ClientFacade;
import com.aviv.MainProjectFS.facades.Exceptions.DoNotExistsException;
import com.aviv.MainProjectFS.facades.Exceptions.LoginFailedException;
import com.aviv.MainProjectFS.loginManager.ClientType;
import com.aviv.MainProjectFS.loginManager.LoginManager;

@RestController
@CrossOrigin
@RequestMapping(path = "login")
public class LoginController {

	@Autowired
	private Map<String, Session> tokensMap;
	
	
	@Autowired
	private LoginManager loginM;
	
	@PostMapping(path = "/{email}/{password}/{type}")
	public ResponseEntity<String> login(@PathVariable("email") String email, @PathVariable("password") String password, @PathVariable("type") String type){
		
		Session s = new Session();
		ClientFacade facade = null;
		String token = UUID.randomUUID().toString();
		
		long lastAccessed = System.currentTimeMillis();
		
		try {
			
			facade = loginM.login(email, password, ClientType.valueOf(type));
			s.setFacade(facade);
			s.setLastAccess(lastAccessed);
			
			tokensMap.put(token, s);
			
			return new ResponseEntity<String>("{\"token\":\"" + token + "\"}", HttpStatus.OK);
		
		}catch(LoginFailedException | DoNotExistsException e) {
			
			return new ResponseEntity<String>("{\"error\":\""+e.getMessage()+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
			
			
			
		
		
	
	
	}
	
}
