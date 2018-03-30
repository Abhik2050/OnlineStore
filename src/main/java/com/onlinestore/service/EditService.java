package com.onlinestore.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.onlinestore.model.Products;
@Service
public class EditService {
	public Products editProduct(Map<String,Products> productMap,String id) {
		
		Products products=productMap.remove(id);
		return products;
	}
}
