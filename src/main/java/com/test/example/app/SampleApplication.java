package com.test.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@ComponentScan("com.test")
@EnableMongoRepositories("com.test")
@EnableAutoConfiguration
public class SampleApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class);
	}

	
}
