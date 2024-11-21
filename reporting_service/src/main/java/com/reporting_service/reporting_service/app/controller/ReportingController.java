package com.reporting_service.reporting_service.app.controller;

import com.reporting_service.reporting_service.app.service.ReportingService;
import com.reporting_service.reporting_service.domain.AttendanceReport;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {
    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AttendanceReport>> getReportsByUser(@PathVariable Long userId) {
        List<AttendanceReport> reports = reportingService.getReportsByUser(userId);
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<AttendanceReport>> getReportsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(reportingService.getReportsByDateRange(startDate, endDate));
    }

    @PostMapping("/generate")
    public ResponseEntity<AttendanceReport> generateReport(
            @RequestParam Long userId,
            @RequestParam int totalCheckIns,
            @RequestParam int punctualCheckIns) {
        return ResponseEntity.ok(reportingService.generateReport(userId, totalCheckIns, punctualCheckIns));
    }
}
