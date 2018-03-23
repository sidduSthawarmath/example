package com.test.example.processor;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
	
		from("file:C:\\Users\\siddayya.bs\\Desktop\\producer").to("file:C:\\Users\\siddayya.bs\\Desktop\\consumer");
		
	}



}
