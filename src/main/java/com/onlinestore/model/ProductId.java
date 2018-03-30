package com.onlinestore.model;

import org.springframework.stereotype.Component;

@Component
public class ProductId {
String id;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}
}
