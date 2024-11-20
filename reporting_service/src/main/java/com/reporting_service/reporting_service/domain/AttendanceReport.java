package com.reporting_service.reporting_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class AttendanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private LocalDate reportDate;
    private int totalCheckIns;
    private int punctualCheckIns;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public int getTotalCheckIns() {
        return totalCheckIns;
    }

    public void setTotalCheckIns(int totalCheckIns) {
        this.totalCheckIns = totalCheckIns;
    }

    public int getPunctualCheckIns() {
        return punctualCheckIns;
    }

    public void setPunctualCheckIns(int punctualCheckIns) {
        this.punctualCheckIns = punctualCheckIns;
    }
}
