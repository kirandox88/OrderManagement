package com.demo.actest.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.demo.actest" })

public class OrderManagementDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementDemoApplication.class, args);
	}
}
