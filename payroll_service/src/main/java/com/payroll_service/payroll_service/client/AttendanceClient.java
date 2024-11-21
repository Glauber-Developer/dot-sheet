package com.payroll_service.payroll_service.client;

import com.payroll_service.payroll_service.domain.Attendance;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "attendance-service")
public interface AttendanceClient {
    @GetMapping("/attendance/{employeeId}")
    List<Attendance> getAttendancesByEmployee(@PathVariable("employeeId") String employeeId);
}