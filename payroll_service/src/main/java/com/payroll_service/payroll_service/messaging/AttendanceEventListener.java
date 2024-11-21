package com.payroll_service.payroll_service.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.payroll_service.payroll_service.app.service.PayrollService;
import com.payroll_service.payroll_service.domain.Attendance;

@Component
public class AttendanceEventListener {

    private final PayrollService payrollService;

    public AttendanceEventListener(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @RabbitListener(queues = "attendance.payroll.queue")
    public void handleAttendanceEvent(Attendance attendance) {
        payrollService.processAttendanceEvent(attendance);
    }

    @RabbitListener(queues = "user.payroll.queue")
    public void handleUserEvent(String message) {
        System.out.println("Received user event: " + message);
    }
}