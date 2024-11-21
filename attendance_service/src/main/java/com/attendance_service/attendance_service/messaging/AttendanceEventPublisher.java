package com.attendance_service.attendance_service.messaging;

import com.attendance_service.attendance_service.modal.Attendance;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AttendanceEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public AttendanceEventPublisher(RabbitTemplate rabbitTemplate,
                                    @Value("${attendance.rabbitmq.exchange}") String exchange,
                                    @Value("${attendance.rabbitmq.routingkey}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void publishAttendanceEvent(Attendance attendance) {
        rabbitTemplate.convertAndSend(exchange, routingKey, attendance);
    }
}