package com.attendance_service.attendance_service.infrastructure.repository;

import com.attendance_service.attendance_service.domain.Attendance;
import com.attendance_service.attendance_service.infrastructure.repository.AttendanceRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AttendanceRepository attendanceRepository;

    public DataInitializer(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Attendance[] attendances = {
                new Attendance(null, "E001", LocalDateTime.now().minusHours(8), LocalDateTime.now().minusHours(1), LocalDateTime.now().minusDays(1)),
                new Attendance(null, "E002", LocalDateTime.now().minusDays(1).minusHours(7), LocalDateTime.now().minusDays(1).minusHours(2), LocalDateTime.now().minusDays(2)),
                new Attendance(null, "E003", LocalDateTime.now().minusDays(2).minusHours(9), LocalDateTime.now().minusDays(2).minusHours(3), LocalDateTime.now().minusDays(3)),
                new Attendance(null, "E004", LocalDateTime.now().minusDays(3).minusHours(6), LocalDateTime.now().minusDays(3).minusHours(1), LocalDateTime.now().minusDays(4)),
                new Attendance(null, "E005", LocalDateTime.now().minusDays(4).minusHours(8), LocalDateTime.now().minusDays(4).minusHours(2), LocalDateTime.now().minusDays(5))
        };

        attendanceRepository.saveAll(Arrays.asList(attendances));
        System.out.println("Banco de dados inicializado com dados de registros de presença.");
    }
}
