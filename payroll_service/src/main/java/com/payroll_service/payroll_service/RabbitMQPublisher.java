package com.payroll_service.payroll_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.payroll_service.payroll_service.domain.User;

@Component
public class RabbitMQPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend("reporting-exchange", "reporting-routing-key", user);
    }
}