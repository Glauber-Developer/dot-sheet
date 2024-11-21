package com.reporting_service.reporting_service;

import com.reporting_service.reporting_service.app.service.ReportingService;
import com.reporting_service.reporting_service.domain.AttendanceReport;
import com.reporting_service.reporting_service.infrastructure.repository.AttendanceReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportingServiceTest {
    @Mock
    private AttendanceReportRepository reportRepository;

    @InjectMocks
    private ReportingService reportingService;

    @Test
    void testGetReportsByUser() {
        Long userId = 1L;
        AttendanceReport report1 = new AttendanceReport();
        AttendanceReport report2 = new AttendanceReport();
        when(reportRepository.findByUserId(userId)).thenReturn(Arrays.asList(report1, report2));

        List<AttendanceReport> result = reportingService.getReportsByUser(userId);

        assertEquals(2, result.size());
        assertTrue(result.contains(report1));
        assertTrue(result.contains(report2));
        verify(reportRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testGetReportsByDateRange() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 31);
        AttendanceReport report1 = new AttendanceReport();
        AttendanceReport report2 = new AttendanceReport();
        when(reportRepository.findByReportDateBetween(startDate, endDate)).thenReturn(Arrays.asList(report1, report2));

        List<AttendanceReport> result = reportingService.getReportsByDateRange(startDate, endDate);

        assertEquals(2, result.size());
        assertTrue(result.contains(report1));
        assertTrue(result.contains(report2));
        verify(reportRepository, times(1)).findByReportDateBetween(startDate, endDate);
    }

    @Test
    void testGenerateReport() {
        Long userId = 1L;
        int totalCheckIns = 10;
        int punctualCheckIns = 8;

        AttendanceReport savedReport = new AttendanceReport();
        savedReport.setUserId(userId);
        savedReport.setReportDate(LocalDate.now());
        savedReport.setTotalCheckIns(totalCheckIns);
        savedReport.setPunctualCheckIns(punctualCheckIns);

        when(reportRepository.save(any(AttendanceReport.class))).thenReturn(savedReport);

        AttendanceReport result = reportingService.generateReport(userId, totalCheckIns, punctualCheckIns);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals(totalCheckIns, result.getTotalCheckIns());
        assertEquals(punctualCheckIns, result.getPunctualCheckIns());
        verify(reportRepository, times(1)).save(any(AttendanceReport.class));
    }


}