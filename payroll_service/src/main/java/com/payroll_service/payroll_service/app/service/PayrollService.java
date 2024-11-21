package com.payroll_service.payroll_service.app.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.payroll_service.payroll_service.domain.Attendance;
import com.payroll_service.payroll_service.domain.User;
import com.payroll_service.payroll_service.infrastructure.repository.UserRepository;

@Service
public class PayrollService {

    private final UserRepository userRepository;

    public PayrollService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void processAttendanceEvent(Attendance attendance) {
        User user = userRepository.findByLogin(attendance.getEmployeeId());
        if (user != null && attendance.getCheckOutTime() != null) {
            long workedHours = Duration.between(attendance.getCheckInTime(), attendance.getCheckOutTime()).toHours();
            user.setWorkedHours(user.getWorkedHours() + workedHours);

            userRepository.save(user);
        }
    }
}