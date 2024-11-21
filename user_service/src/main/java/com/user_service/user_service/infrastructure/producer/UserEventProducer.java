package com.user_service.user_service.infrastructure.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public UserEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUserCreatedEvent(String userId) {
        String message = String.format("User created with ID: %s", userId);
        rabbitTemplate.convertAndSend("user.events", "user.created", message);
    }

    public void sendUserUpdatedEvent(String userId) {
        String message = String.format("User updated with ID: %s", userId);
        rabbitTemplate.convertAndSend("user.events", "user.updated", message);
    }
}
