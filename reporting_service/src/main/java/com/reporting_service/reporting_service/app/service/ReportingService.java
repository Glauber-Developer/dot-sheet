package com.reporting_service.reporting_service.app.service;

import com.reporting_service.reporting_service.domain.AttendanceReport;
import com.reporting_service.reporting_service.infrastructure.repository.AttendanceReportRepository;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingService {
    private final AttendanceReportRepository reportRepository;

    public ReportingService(AttendanceReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

public List<AttendanceReport> getReportsByUser(Long userId) {
    return reportRepository.findByUserId(userId).stream()
            .map(obj -> (AttendanceReport) obj)
            .collect(Collectors.toList());
}
public List<AttendanceReport> getReportsByDateRange(LocalDate startDate, LocalDate endDate) {
    return reportRepository.findByReportDateBetween(startDate, endDate).stream()
            .map(obj -> (AttendanceReport) obj)
            .collect(Collectors.toList());
}

    public AttendanceReport generateReport(Long userId, int totalCheckIns, int punctualCheckIns) {
        AttendanceReport report = new AttendanceReport();
        report.setUserId(userId);
        report.setReportDate(LocalDate.now());
        report.setTotalCheckIns(totalCheckIns);
        report.setPunctualCheckIns(punctualCheckIns);
        return reportRepository.save(report);
    }
}
