package com.onlinestore.validator;

import java.util.function.Predicate;


import com.onlinestore.model.Products;

public class Vallidation {
public static boolean Validate(Products products) {
	if(null==products) {
		return false;
	}
	if(!predicate.test(products.getId()) || !predicate.test(products.getQty())||!predicate.test(products.getName())) {
	return false;	
	}
	return true;
}
public static Predicate<String> predicate=s->( null != s && s.trim().length() > 0);

}
