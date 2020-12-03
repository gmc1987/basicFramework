package com.basic.framework.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableResourceServer
@ComponentScan(basePackages="com.basic.framework.platform.*")
public class ServicePlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePlatformApplication.class, args);
	}

}
