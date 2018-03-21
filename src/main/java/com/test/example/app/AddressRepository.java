package com.test.example.app;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.example.domain.Address;

public interface AddressRepository extends MongoRepository<Address, Integer> {

	
}
