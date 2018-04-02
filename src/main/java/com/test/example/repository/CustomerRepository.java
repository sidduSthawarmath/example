package com.test.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.example.domain.Customer;


@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>,CustomCustomerRepo {

	@Query(value = "{}", fields = "{lastName : 1,firstName:1}")
	List<Customer> findAllUsingProjection();

	
	@Query(value="{lastName:?0}")
	List<Customer> getByLastNames(String lastName);

	
}
