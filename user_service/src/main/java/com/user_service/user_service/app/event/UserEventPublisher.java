package com.user_service.user_service.app.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public UserEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishUserCreatedEvent(String userJson) {
        rabbitTemplate.convertAndSend("user.direct", "user.created", userJson);
    }

    public void publishUserUpdatedEvent(String userJson) {
        rabbitTemplate.convertAndSend("user.direct", "user.updated", userJson);
    }
}
