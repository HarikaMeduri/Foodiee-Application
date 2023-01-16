package com.niit.ApiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator getRoute(RouteLocatorBuilder builder){
		return builder.routes()
				.route(p-> p.path("/api/v1/**").uri("lb://user-authentication-service"))
				.route(p->p.path("/api/v2/**").uri("lb://customer-service"))
				.route(p->p.path("/api/v3/**").uri("lb://restaurant-service"))
				.build();

	}
}
