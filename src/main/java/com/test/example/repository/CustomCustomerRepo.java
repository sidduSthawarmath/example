package com.test.example.repository;

import java.util.List;

import com.test.example.domain.AddressAggregation;
import com.test.example.domain.Customer;

public interface CustomCustomerRepo{

	
	List<Customer> getDetailsByName();

	List<AddressAggregation> getCityCount();


	
	
}
