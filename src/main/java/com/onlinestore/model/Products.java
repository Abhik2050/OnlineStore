package com.onlinestore.model;

import java.io.File;

import org.springframework.stereotype.Component;

@Component
public class Products {
private String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getQty() {
	return qty;
}
public void setQty(String qty) {
	this.qty = qty;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
private String qty;
private String name;
private String description;
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public File getImage() {
	return image;
}
public void setImage(File image) {
	this.image = image;
}
private File image;
}
