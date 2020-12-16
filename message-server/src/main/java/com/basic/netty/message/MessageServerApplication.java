package com.basic.netty.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.basic.netty.message.server.IMessageServer;

@SpringBootApplication
@EnableDiscoveryClient
public class MessageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageServerApplication.class, args);
		IMessageServer server = new IMessageServer();
		server.messageServerStart();
	}

}
