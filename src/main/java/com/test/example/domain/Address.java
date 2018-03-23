package com.test.example.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {

@Id
private Integer addressId;

@Indexed
private String streetName;

@Indexed
private String locality;

@Indexed
private String city;

public Integer getAddressId() {
	return addressId;
}

public void setAddressId(Integer addressId) {
	this.addressId = addressId;
}

public String getStreetName() {
	return streetName;
}

public void setStreetName(String streetName) {
	this.streetName = streetName;
}

public String getLocality() {
	return locality;
}

public void setLocality(String locality) {
	this.locality = locality;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

@Override
public String toString() {
	return "Address [addressId=" + addressId + ", streetName=" + streetName + ", locality=" + locality + ", city="
			+ city + "]";
}

}