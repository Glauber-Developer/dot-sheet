package com.user_service.user_service;

import com.user_service.user_service.domain.User;
import com.user_service.user_service.infrastructure.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User[] users = {
                new User( "Lucas Ribeiro", "Trainee", "lucasribeiro", "lr0025"),
                new User( "Gabriel Martins", "Developer", "gabrielmartins", "gm1902"),
                new User( "João Vogel", "Developer", "joaovogel", "jv8673"),
                new User( "Gabriel Fogaça", "Data Analyst", "gabrielfogaca", "gf8347"),
                new User( "Glauber Araujo", "Project Manager", "glauberaraujo", "ga0102")
        };
        userRepository.saveAll(Arrays.asList(users));
        System.out.println("Banco de dados inicializado com dados de teste.");
    }
}