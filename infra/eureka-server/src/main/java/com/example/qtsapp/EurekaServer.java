package com.example.qtsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {
		System.err.println("============================================================================");
		System.err.println("AWS ELB should do the service discovery in ECS deployment, this is not needed");
		System.err.println("============================================================================");
		SpringApplication.run(EurekaServer.class, args);
	}
}
