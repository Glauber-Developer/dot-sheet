package com.reporting_service.reporting_service.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Nome das filas
    public static final String ATTENDANCE_REPORTING_QUEUE = "attendance.reporting.queue";
    public static final String PAYROLL_REPORTING_QUEUE = "payroll.reporting.queue";

    // Declaração da fila de Attendance
    @Bean
    public Queue attendanceReportingQueue() {
        return new Queue(ATTENDANCE_REPORTING_QUEUE, true); // Fila durável
    }

    // Declaração da fila de Payroll
    @Bean
    public Queue payrollReportingQueue() {
        return new Queue(PAYROLL_REPORTING_QUEUE, true); // Fila durável
    }
}
