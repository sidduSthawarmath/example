package com.test.example.autoincrservices;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class AutoIncIdConfigService {

	@Autowired 
	private MongoOperations mongo;
	   
	  public int getNextSequence(String collectionName) {
	 
	    Counter counter = mongo.findAndModify(
	      query(where("_id").is(collectionName)), 
	      new Update().inc("seq", 1),
	      options().returnNew(true).upsert(true),
	      Counter.class);
	       
	    return counter.getSeq();
	  }
	  
}
