package com.payroll_service.payroll_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PayrollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollServiceApplication.class, args);
	}

}
