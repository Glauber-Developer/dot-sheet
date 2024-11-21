package com.payroll_service.payroll_service.repository;

import com.payroll_service.payroll_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

}