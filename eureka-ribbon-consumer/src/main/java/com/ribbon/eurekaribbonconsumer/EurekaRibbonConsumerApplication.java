package com.ribbon.eurekaribbonconsumer;

import com.ribbon.config.SpringCludeApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringCludeApplication
@ComponentScan("com.ribbon.study.*")
@EnableCircuitBreaker
public class EurekaRibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRibbonConsumerApplication.class, args);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
