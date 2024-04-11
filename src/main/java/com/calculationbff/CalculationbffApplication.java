package com.calculationbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CalculationbffApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculationbffApplication.class, args);
	}

}