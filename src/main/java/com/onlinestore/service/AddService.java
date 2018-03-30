package com.onlinestore.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.onlinestore.model.Products;

@Service
public class AddService {
public void addProducts(Map<String,Products> productMap,Products products) {
	
	String id =products.getId();
	productMap.put(id, products);
	
}
}
