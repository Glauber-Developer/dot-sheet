package com.payroll_service.payroll_service;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        servers = {
                @Server(url = "https://localhost:8080")
        },
        info = @Info(
                title = "DotSheet",
                version = "v1",
                description =  """
                    DotSheet is a Time and Attendance Management System
                    
                    Development Team:
                    - Glauber Araujo (dev1@email.com)
                    - Gabriel Fogaça (dev2@email.com)
                    - Gabriel Martins (dev3@email.com)
                    - João Vogel (dev3@email.com)
                    - Lucas Ribeiro (dev3@email.com)
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
