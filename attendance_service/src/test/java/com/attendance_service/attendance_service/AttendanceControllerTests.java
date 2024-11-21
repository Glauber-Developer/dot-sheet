package com.attendance_service.attendance_service;

import com.attendance_service.attendance_service.controller.AttendanceController;
import com.attendance_service.attendance_service.model.Attendance;
import com.attendance_service.attendance_service.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttendanceControllerTests {

    @Mock
    private AttendanceService attendanceService;

    @InjectMocks
    private AttendanceController attendanceController;

    public AttendanceControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckIn() {
        String employeeId = "EMP001";
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());

        when(attendanceService.registerCheckIn(employeeId)).thenReturn(attendance);

        ResponseEntity<Attendance> response = attendanceController.checkIn(employeeId);

        assertNotNull(response.getBody());
        assertEquals(employeeId, response.getBody().getEmployeeId());
        verify(attendanceService, times(1)).registerCheckIn(employeeId);
    }

    @Test
    void testCheckOut() {
        String employeeId = "EMP001";
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());
        attendance.setCheckOutTime(LocalDateTime.now().plusHours(8));

        when(attendanceService.registerCheckOut(employeeId)).thenReturn(attendance);

        ResponseEntity<Attendance> response = attendanceController.checkOut(employeeId);

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getCheckOutTime());
        verify(attendanceService, times(1)).registerCheckOut(employeeId);
    }

    @Test
    void testGetAttendancesByEmployee() {
        String employeeId = "EMP001";
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());

        when(attendanceService.getAttendanceByEmployeeId(employeeId)).thenReturn(List.of(attendance));

        ResponseEntity<List<Attendance>> response = attendanceController.getAttendancesByEmployee(employeeId);

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(employeeId, response.getBody().get(0).getEmployeeId());
        verify(attendanceService, times(1)).getAttendanceByEmployeeId(employeeId);
    }
}
