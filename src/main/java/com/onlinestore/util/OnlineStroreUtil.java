package com.onlinestore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.onlinestore.model.Products;
@Component
public class OnlineStroreUtil {
	private static final Logger LOGGER = LogManager.getLogger(OnlineStroreUtil.class);
	public List<Products> populateProducts(Map<String, Products> productMap) {
		 List<Products> productList=new ArrayList<Products>();
		 if(null!=productMap) {
		for (String key: productMap.keySet()) {
			LOGGER.info("key : " + key);
			LOGGER.info("value : " + productMap.get(key));
		    productList.add(productMap.get(key));
		}
		 }
		return productList;
}
}
