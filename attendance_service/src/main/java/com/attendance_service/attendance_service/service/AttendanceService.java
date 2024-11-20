package com.attendance_service.attendance_service.service;

import com.attendance_service.attendance_service.modal.Attendance;
import com.attendance_service.attendance_service.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Registrar check-in
    public Attendance registerCheckIn(String employeeId) {
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());
        return attendanceRepository.save(attendance);
    }

    // Registrar check-out
    public Attendance registerCheckOut(String employeeId) {
        Optional<Attendance> optionalAttendance = attendanceRepository
                .findTopByEmployeeIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(employeeId);

        if (optionalAttendance.isPresent()) {
            Attendance attendance = optionalAttendance.get();
            attendance.setCheckOutTime(LocalDateTime.now());
            return attendanceRepository.save(attendance);
        } else {
            throw new IllegalArgumentException("Nenhum registro de check-in ativo encontrado para o funcionário: " + employeeId);
        }
    }

    // Listar registros por funcionário
    public List<Attendance> getAttendanceByEmployeeId(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }
}
