package com.api_gateway.api_gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ApiGatewayApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testUserServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer your_jwt_token");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/users", String.class, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testPayrollServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer your_jwt_token");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/payroll", String.class, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testAttendanceServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer your_jwt_token");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/attendance", String.class, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testReportingServiceRoute() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer your_jwt_token");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/reporting", String.class, headers);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}