package com.onlinestore.model;

public class Response {
private String token;
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
private String message;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
private String code;
private String nextPage;

public String getNextPage() {
	return nextPage;
}
public void setNextPage(String nextPage) {
	this.nextPage = nextPage;
}
private String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}
