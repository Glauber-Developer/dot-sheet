package com.user_service.user_service.app;

import com.user_service.user_service.RabbitMQPublisher;
import com.user_service.user_service.domain.User;
import com.user_service.user_service.infrastructure.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private RabbitMQPublisher rabbitMQPublisher;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<User> getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public User createUser(User user){
        User savedUser = userRepository.save(user);
        rabbitMQPublisher.sendMessage(savedUser);
        return savedUser;
    }

    public ResponseEntity<User> updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.setId(id);
        rabbitMQPublisher.sendMessage(user);

        return ResponseEntity.ok(userRepository.save(user));
    }

    public ResponseEntity<Void> deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}