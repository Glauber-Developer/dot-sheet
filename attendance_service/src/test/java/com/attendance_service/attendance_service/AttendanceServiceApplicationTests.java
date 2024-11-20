package com.attendance_service.attendance_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Isso força o uso de application-test.yml
class AttendanceServiceApplicationTests {
    @Test
    void contextLoads() {
        // Verifica se o contexto do Spring foi carregado
    }
}

