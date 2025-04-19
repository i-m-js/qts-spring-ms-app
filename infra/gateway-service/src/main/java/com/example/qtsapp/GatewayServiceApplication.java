package com.example.qtsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication {
	@Autowired
	private EurekaDiscoveryClient discoveryClient;

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@PostConstruct
	void postConstruct() {
		System.err.println(discoveryClient.getInstances("quote-app").size());
	}
//    @Bean
//    RouteLocator myRoutes(RouteLocatorBuilder builder) {
//		return builder.routes().route(
//				p -> p.path("/get").filters(f -> f.addRequestHeader("Hello", "World")).uri("http://httpbin.org:80"))
//				.build();
//	}

//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes().route("service1", r -> r.path("/api/quotes/**").uri("lb://QUOTE-APP"))
//				.route("service2", r -> r.path("/service2/**").filters(f -> f.stripPrefix(1)).uri("lb://ADMIN-APP"))
//				.build();
//	}
}
