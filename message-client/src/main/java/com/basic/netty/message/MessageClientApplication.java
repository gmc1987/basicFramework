package com.basic.netty.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MessageClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageClientApplication.class, args);
	}

}
