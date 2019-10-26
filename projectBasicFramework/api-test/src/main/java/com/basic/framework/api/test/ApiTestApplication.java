package com.basic.framework.api.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTestApplication.class, args);
	}

}
