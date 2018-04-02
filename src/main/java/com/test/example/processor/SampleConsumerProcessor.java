package com.test.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SampleConsumerProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
	System.out.println(exchange.getIn().getBody());
		
	}

}
