package com.reporting_service.reporting_service;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "http://localhost:8080", description = "API Gateway"),
                @Server(url = "http://localhost:8084", description = "User Service"),
                @Server(url = "http://localhost:8086", description = "Attendance Service"),
                @Server(url = "http://localhost:8087", description = "Payroll Service"),
                @Server(url = "http://localhost:8085", description = "Reporting Service")
        },
        info = @Info(
                title = "DotSheet API Gateway",
                version = "v1",
                description = """
                    DotSheet is a Time and Attendance Management System
                    
                    Service Endpoints:
                    - User Service: http://localhost:8084/swagger-ui.html
                    - Attendance Service: http://localhost:8086/swagger-ui.html
                    - Payroll Service: http://localhost:8087/swagger-ui.html
                    - Reporting Service: http://localhost:8085/swagger-ui.html
                    - Eureka Server: http://localhost:8761
                    """,
                license = @License(
                        name = "MIT License",
                        url = "https://mit-license.org/"
                ),
                contact = @Contact(
                        url = "https://github.com/Glauber-Developer/dot-sheet"
                )
        )
)
public class OpenApiConfig {
}
