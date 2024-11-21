// package com.user_service.user_service.service;

// import com.user_service.user_service.RabbitMQPublisher;
// import com.user_service.user_service.model.User;
// import com.user_service.user_service.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private RabbitMQPublisher rabbitMQPublisher;

//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     public User saveUser(User user) {
//         User savedUser = userRepository.save(user);
//         rabbitMQPublisher.sendMessage(savedUser);
//         return savedUser;
//     }
// }