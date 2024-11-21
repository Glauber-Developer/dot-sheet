package com.attendance_service.attendance_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.attendance_service.attendance_service.model.Attendance;
import com.attendance_service.attendance_service.service.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Endpoint para registrar check-in
    @PostMapping("/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestParam String employeeId) {
        Attendance attendance = attendanceService.registerCheckIn(employeeId);
        return ResponseEntity.ok(attendance);
    }

    // Endpoint para registrar check-out
    @PutMapping("/checkout")
    public ResponseEntity<Attendance> checkOut(@RequestParam String employeeId) {
        Attendance attendance = attendanceService.registerCheckOut(employeeId);
        return ResponseEntity.ok(attendance);
    }

    // Endpoint para listar registros de um funcionário
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendancesByEmployee(@PathVariable String employeeId) {
        List<Attendance> attendances = attendanceService.getAttendanceByEmployeeId(employeeId);
        return ResponseEntity.ok(attendances);
    }
}
