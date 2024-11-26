package com.reporting_service.reporting_service;

import com.reporting_service.reporting_service.domain.AttendanceReport;
import com.reporting_service.reporting_service.infrastructure.repository.AttendanceReportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner{

    private final AttendanceReportRepository attendanceReportRepository;

    public DataInitializer(AttendanceReportRepository attendanceReportRepository) {
        this.attendanceReportRepository = attendanceReportRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AttendanceReport[] reports = {
                new AttendanceReport(null, 1L, LocalDate.now().minusDays(3), 5, 4),
                new AttendanceReport(null, 2L, LocalDate.now().minusDays(2), 4, 4),
                new AttendanceReport(null, 3L, LocalDate.now().minusDays(1), 3, 2),
                new AttendanceReport(null, 4L, LocalDate.now(), 6, 5),
                new AttendanceReport(null, 5L, LocalDate.now().minusDays(5), 2, 2)
        };
        attendanceReportRepository.saveAll(Arrays.asList(reports));
        System.out.println("Banco de dados inicializado com dados de relatórios de presença.");
    }
}
