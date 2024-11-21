package com.user_service.user_service;

import com.user_service.user_service.domain.User;
import com.user_service.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User[] users = {
                new User("Lucas Ribeiro", "Trainee", "lucasribeiro", "lr0025", 15.0),
                new User("Gabriel Martins", "Developer", "gabrielmartins", "gm1902", 25.0),
                new User("João Vogel", "Developer", "joaovogel", "jv8673", 25.0),
                new User("Gabriel Fogaça", "Data Analyst", "gabrielfogaca", "gf8347", 20.0),
                new User("Glauber Araujo", "Project Manager", "glauberaraujo", "ga0102", 30.0)
        };
        userRepository.saveAll(Arrays.asList(users));
        System.out.println("Banco de dados inicializado com dados de teste.");
    }
}