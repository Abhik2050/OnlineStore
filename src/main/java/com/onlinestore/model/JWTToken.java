package com.onlinestore.model;

import org.springframework.stereotype.Component;

@Component
public class JWTToken {
private String token;

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

}
