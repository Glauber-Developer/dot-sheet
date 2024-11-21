package com.payroll_service.payroll_service.app.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.payroll_service.payroll_service.RabbitMQPublisher;
import com.payroll_service.payroll_service.domain.Attendance;
import com.payroll_service.payroll_service.domain.User;
import com.payroll_service.payroll_service.infrastructure.repository.UserRepository;

@Service
public class PayrollService {

    private final UserRepository userRepository;
    private final RabbitMQPublisher rabbitMQPublisher;

    public PayrollService(UserRepository userRepository, RabbitMQPublisher rabbitMQPublisher) {
        this.userRepository = userRepository;
        this.rabbitMQPublisher = rabbitMQPublisher;
    }

    public void processAttendanceEvent(Attendance attendance) {
        User user = userRepository.findByLogin(attendance.getEmployeeId());
        if (user != null && attendance.getCheckOutTime() != null) {
            long workedHours = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime()).toHours();
            user.setWorkedHours(user.getWorkedHours() + workedHours);

            updatePayroll(user, workedHours);

            userRepository.save(user);

            rabbitMQPublisher.sendMessage(user);
        }
    }

    private void updatePayroll(User user, long workedHours) {
        double hourlyRate = user.getHourlyRate();
        double additionalPay = workedHours * hourlyRate;
        user.setTotalPay(user.getTotalPay() + additionalPay);
    }
}