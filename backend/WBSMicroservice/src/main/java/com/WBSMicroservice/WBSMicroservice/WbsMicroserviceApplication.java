package com.WBSMicroservice.WBSMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.WBSMicroservice.model")
public class WbsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WbsMicroserviceApplication.class, args);
	}

}
