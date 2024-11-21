package com.reporting_service.reporting_service.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reporting_service.reporting_service.domain.AttendanceReport;

@Repository
public interface AttendanceReportRepository extends JpaRepository<AttendanceReport, Long> {
    List<AttendanceReport> findByUserId(Long userId);
    List<AttendanceReport> findByReportDateBetween(LocalDate startDate, LocalDate endDate);
}
