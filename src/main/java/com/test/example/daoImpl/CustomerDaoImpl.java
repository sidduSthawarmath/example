package com.test.example.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.test.example.domain.Customer;
import com.test.example.repository.CustomerDao;

@Component
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Customer> getCustDetByName() {
		String name="Siddu";
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is(name));
		return mongoTemplate.find(query, Customer.class);
	}


	


	
}
