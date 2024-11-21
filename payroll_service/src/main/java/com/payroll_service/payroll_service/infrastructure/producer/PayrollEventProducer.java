package com.payroll_service.payroll_service.infrastructure.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PayrollEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public PayrollEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPayrollClosedEvent(String payrollId) {
        String message = String.format("Payroll closed with ID: %s", payrollId);
        rabbitTemplate.convertAndSend("payroll.events", "payroll.closed", message);
    }
}
