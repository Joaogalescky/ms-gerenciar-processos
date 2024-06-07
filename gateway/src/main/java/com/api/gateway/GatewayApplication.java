package com.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/propostas/**")
						.uri("http://localhost:8081/api/v1/propostas"))
				.route(r -> r.path("/api/v1/funcionarios/**")
						.uri("http://localhost:8082/api/v1/funcionarios"))
				.route(r -> r.path("/api/v1/resultado/**")
						.uri("http://localhost:8083/api/v1/resultado"))
				.build();
	}
}
