package com.payroll_service.payroll_service;

import com.payroll_service.payroll_service.domain.User;
import com.payroll_service.payroll_service.infrastructure.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserDataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User[] users = {
                new User(null, "Lucas Ribeiro", "Trainee", "lucasribeiro", "lr0025", 160),
                new User(null, "Gabriel Martins", "Developer", "gabrielmartins", "gm1902", 200),
                new User(null, "João Vogel", "Developer", "joaovogel", "jv8673", 180),
                new User(null, "Gabriel Fogaça", "Data Analyst", "gabrielfogaca", "gf8347", 190),
                new User(null, "Glauber Araujo", "Project Manager", "glauberaraujo", "ga0102", 210)
        };
        userRepository.saveAll(Arrays.asList(users));
        System.out.println("Banco de dados inicializado com dados de teste.");
    }
}
