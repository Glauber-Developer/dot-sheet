package com.attendance_service.attendance_service.infrastructure.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AttendanceEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public AttendanceEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAttendanceRegisteredEvent(String attendanceId) {
        String message = String.format("Attendance registered with ID: %s", attendanceId);
        rabbitTemplate.convertAndSend("attendance.events", "attendance.registered", message);
    }
}
