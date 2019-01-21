package com.ribbon.config;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
public @interface SpringCludeApplication {
}
