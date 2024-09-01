package com.example.api_pncp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiPncpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPncpApplication.class, args);
	}

}
