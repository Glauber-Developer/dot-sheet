package com.payroll_service.payroll_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll_service.payroll_service.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}