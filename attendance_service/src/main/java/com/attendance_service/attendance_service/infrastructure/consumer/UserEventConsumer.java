package com.attendance_service.attendance_service.infrastructure.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @RabbitListener(queues = "user.attendance.queue")
    public void handleUserEvent(String message) {
        System.out.println("Received user event: " + message);
    }
}
