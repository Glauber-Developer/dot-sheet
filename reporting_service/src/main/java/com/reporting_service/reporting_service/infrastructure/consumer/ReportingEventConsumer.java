package com.reporting_service.reporting_service.infrastructure.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReportingEventConsumer {

    @RabbitListener(queues = "attendance.reporting.queue")
    public void handleAttendanceEvent(String message) {
        System.out.println("Received attendance event: " + message);
    }

    @RabbitListener(queues = "payroll.reporting.queue")
    public void handlePayrollEvent(String message) {
        System.out.println("Received payroll event: " + message);
    }
}
