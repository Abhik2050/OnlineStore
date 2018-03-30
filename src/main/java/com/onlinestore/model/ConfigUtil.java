package com.onlinestore.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties("")
public class ConfigUtil {
	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	 private  final Map<String, String> userCredential = new HashMap<>();

	    public Map<String, String> getUserCredential() {
		return userCredential;
	}

		
}
