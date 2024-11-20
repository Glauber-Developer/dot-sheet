package com.attendance_service.attendance_service;

import com.attendance_service.attendance_service.modal.Attendance;
import com.attendance_service.attendance_service.repository.AttendanceRepository;
import com.attendance_service.attendance_service.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AttendanceServiceTests {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    public AttendanceServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterCheckIn() {
        String employeeId = "EMP001";
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());

        when(attendanceRepository.save(any(Attendance.class))).thenReturn(attendance);

        Attendance result = attendanceService.registerCheckIn(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getEmployeeId());
        verify(attendanceRepository, times(1)).save(any(Attendance.class));
    }

    @Test
    void testRegisterCheckOut() {
        String employeeId = "EMP001";

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(LocalDateTime.now());

        // Configura o mock para retornar um registro válido
        when(attendanceRepository.findTopByEmployeeIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(employeeId))
                .thenReturn(Optional.of(attendance));

        Attendance result = attendanceService.registerCheckOut(employeeId);

        assertNotNull(result); // Verifica se o resultado não é nulo
        assertNotNull(result.getCheckOutTime()); // Verifica se o check-out foi registrado
        verify(attendanceRepository, times(1)).save(attendance);
    }


    @Test
    void testRegisterCheckOut_NoCheckIn() {
        String employeeId = "EMP001";

        when(attendanceRepository.findTopByEmployeeIdAndCheckOutTimeIsNullOrderByCheckInTimeDesc(employeeId))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            attendanceService.registerCheckOut(employeeId);
        });

        assertEquals("Nenhum registro de check-in ativo encontrado para o funcionário: " + employeeId, exception.getMessage());
    }
}
