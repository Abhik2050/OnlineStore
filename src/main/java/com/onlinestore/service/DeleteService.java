package com.onlinestore.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.onlinestore.model.Products;

@Service
public class DeleteService {
public Map<String,Products> deleteProduct(Map<String,Products> productMap,String id) {
	
	productMap.remove(id);
	return productMap;
}
}
