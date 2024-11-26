package com.api_gateway.api_gateway;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApiGatewayApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String generateToken() {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject("user")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testUserServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + generateToken());
    
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + 8084 + "/users", String.class, headers);
    
        if (response.getStatusCode() != HttpStatus.OK) {
            System.out.println(port);
            System.out.println("Error: " + response.getBody());
        }
    
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testPayrollServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + generateToken());

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/payroll", String.class, headers);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testAttendanceServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + generateToken());

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/attendance", String.class, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testReportingServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + generateToken());

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/reporting", String.class, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}