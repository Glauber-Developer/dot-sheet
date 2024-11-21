// package com.attendance_service.attendance_service.model;


// import jakarta.persistence.*;

// @Entity
// public class Attendance {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String userId;
//     private String status; // Present, Absent, etc.
//     private String date; // Simplificado como String (pode ser LocalDate)

//     public Attendance() {
//     }

//     public Attendance(String userId, String status, String date) {
//         this.userId = userId;
//         this.status = status;
//         this.date = date;
//     }

//     // Getters e Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getUserId() {
//         return userId;
//     }

//     public void setUserId(String userId) {
//         this.userId = userId;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public String getDate() {
//         return date;
//     }

//     public void setDate(String date) {
//         this.date = date;
//     }
// }