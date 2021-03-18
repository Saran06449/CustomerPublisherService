package com.prokarma.engineering.excellence.customer.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CustomerPublisherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerPublisherServiceApplication.class, args);
	}

}
