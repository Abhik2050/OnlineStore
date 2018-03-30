package com.onlinestore.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinestore.model.ConfigUtil;
import com.onlinestore.model.LoginCredential;

@Service
public class LoginService {
	private ConfigUtil configUtil;

	   @Autowired
	   public void setConfigUtil( ConfigUtil configUtil ){
	      this.configUtil = configUtil;
	   }
public String authenticate(LoginCredential loginCredential) {
	Map<String, String> userCredential =configUtil.getUserCredential();
	if(null!=userCredential&& null!=loginCredential){
	if(null!=loginCredential.getUserid()) {
		
		String password=userCredential.get(loginCredential.getUserid());
		if(null!=loginCredential.getPassword() && null!=password) {
			if(password.equalsIgnoreCase(loginCredential.getPassword()) ){
				return "success";
			}
			
		}
	}
	}
	
	return "fail";
}


	   
}
