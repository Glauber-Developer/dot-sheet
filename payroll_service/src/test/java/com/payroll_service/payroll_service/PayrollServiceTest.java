package com.payroll_service.payroll_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.payroll_service.payroll_service.app.service.PayrollService;
import com.payroll_service.payroll_service.domain.*;
import com.payroll_service.payroll_service.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class PayrollServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private PayrollService payrollService;

    @Test
    void testProcessAttendanceEvent_Success() {
        String employeeId = "employee123";
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.of(2024, 11, 20, 9, 0));
        attendance.setCheckOutTime(LocalDateTime.of(2024, 11, 20, 17, 0));

        User user = new User();
        user.setLogin(employeeId);
        user.setWorkedHours(40);

        when(userRepository.findByLogin(employeeId)).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        payrollService.processAttendanceEvent(attendance);

        assertEquals(48, user.getWorkedHours()); // 40 + 8 horas trabalhadas
        verify(userRepository, times(1)).findByLogin(employeeId);
        verify(userRepository, times(1)).save(user);
    }
}
