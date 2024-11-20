package com.api_gateway.api_gateway;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.WebFilter;

import com.api_gateway.api_gateway.filter.JwtAuthenticationFilter;

@TestConfiguration
public class TestConfig {

    @Bean
    public WebFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}