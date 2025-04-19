package com.example.qtsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class AdminApp {
	public static void main(String[] args) {
		SpringApplication.run(AdminApp.class, args);
	}
}
