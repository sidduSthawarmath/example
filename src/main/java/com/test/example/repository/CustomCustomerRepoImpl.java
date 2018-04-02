package com.test.example.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.test.example.domain.Address;
import com.test.example.domain.AddressAggregation;
import com.test.example.domain.Customer;

public class CustomCustomerRepoImpl implements CustomCustomerRepo {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Customer> getDetailsByName() {
		String name = "Siddu";
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is(name));
		return mongoTemplate.find(query, Customer.class);
	}

	public List<AddressAggregation> getCityCount() {
		Aggregation agg = newAggregation(group("city").count().as("total"),
				project("total").and("city").previousOperation(), sort(Sort.Direction.ASC, "total"));

		AggregationResults<AddressAggregation> addr = mongoTemplate.aggregate(agg, Address.class,
				AddressAggregation.class);

		return addr.getMappedResults();
	}

}
