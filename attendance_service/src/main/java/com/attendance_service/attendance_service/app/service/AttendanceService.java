package com.attendance_service.attendance_service.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance_service.attendance_service.RabbitMQPublisher;
import com.attendance_service.attendance_service.domain.Attendance;
import com.attendance_service.attendance_service.infrastructure.repository.AttendanceRepository;
@Service
public class AttendanceService {
    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Registrar check-in
    public Attendance registerCheckIn(String employeeId) {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());
        attendance = attendanceRepository.save(attendance);
        rabbitMQPublisher.sendMessage(attendance);
        return attendance;
    }

    // Registrar check-out
    public Attendance registerCheckOut(String employeeId) {
        Optional<Attendance> optionalAttendance = attendanceRepository
                .findTopByEmployeeIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(employeeId);

        if (optionalAttendance.isPresent()) {
            Attendance attendance = optionalAttendance.get();
            attendance.setCheckOutTime(LocalDateTime.now());
            attendance = attendanceRepository.save(attendance);
            rabbitMQPublisher.sendMessage(attendance);
            return attendance;
        } else {
            throw new IllegalArgumentException("Nenhum registro de check-in ativo encontrado para o funcionário: " + employeeId);
        }
    }

    // Listar registros por funcionário
    public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
}