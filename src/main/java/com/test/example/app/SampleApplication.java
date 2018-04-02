package com.test.example.app;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.test.example.processor.SampleRoute;

@SpringBootApplication
@ComponentScan("com.test")
@EnableMongoRepositories("com.test")
@EnableAutoConfiguration
public class SampleApplication {

	@Autowired
	SampleRoute sampleRoute;

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class);
	}

	public CamelContext routeConfig() {
		CamelContext ctx = new DefaultCamelContext();
		try {
			ctx.addRoutes(sampleRoute);
			ctx.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ctx;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
