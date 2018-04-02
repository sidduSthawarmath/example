package com.test.example.processor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleRoute extends RouteBuilder {

	@Autowired
	private SampleConsumerProcessor sampleConsumer;
	
	@Override
	public void configure() throws Exception {
	
	   from("file:D:\\testKafka\\").split().tokenize("\n").to("kafka:localhost:9092?topic=test&brokers=localhost:9092");
		
		from("kafka:localhost:9092?topic=test&groupId=testing&brokers=localhost:9092")
		.process(sampleConsumer);
		
	}



}
