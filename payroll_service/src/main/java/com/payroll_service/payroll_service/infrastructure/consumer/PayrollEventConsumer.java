package com.payroll_service.payroll_service.infrastructure.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PayrollEventConsumer {

    @RabbitListener(queues = "user.payroll.queue")
    public void handleUserEvent(String message) {
        System.out.println("Received user event: " + message);
    }

    @RabbitListener(queues = "attendance.payroll.queue")
    public void handleAttendanceEvent(String message) {
        System.out.println("Received attendance event: " + message);
    }
}
