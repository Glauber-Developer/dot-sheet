package com.user_service.user_service.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_service.user_service.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);
}