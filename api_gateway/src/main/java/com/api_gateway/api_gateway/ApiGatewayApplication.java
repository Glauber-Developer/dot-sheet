package com.api_gateway.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public GlobalFilter customFilter() {
        return (exchange, chain) -> {
            System.out.println("Global Pre Filter executed");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("Global Post Filter executed");
            }));
        };
    }
}