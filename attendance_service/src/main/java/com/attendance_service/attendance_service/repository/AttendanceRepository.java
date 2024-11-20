package com.attendance_service.attendance_service.repository;

import com.attendance_service.attendance_service.modal.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeId(String employeeId);

    Optional<Attendance> findTopByEmployeeIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(String employeeId);
}
