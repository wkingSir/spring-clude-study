package com.example.eurekaserverprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.example.eureka.provider")
public class EurekaServerProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerProviderApplication.class, args);
	}
}
