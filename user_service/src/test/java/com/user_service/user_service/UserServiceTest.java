package com.user_service.user_service;

import com.user_service.user_service.app.service.UserService;
import com.user_service.user_service.domain.User;
import com.user_service.user_service.infrastructure.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testReturnAllUsers(){
        User user1 = new User("Jane","CEO","janeceo","jane123");
        User user2 = new User("James","CFO","jamescfo","james456");
        when(userRepository.findAll()).thenReturn((Arrays.asList(user1, user2)));
        List<User> result = userService.getAllUsers();
        assertThat(result).contains(user1);
        assertThat(result).contains(user2);
    }

    @Test
    void testGetUserById(){
        Long userId = 1L;
        User user = new User();
        when(userRepository.findById(userId)).thenReturn((Optional.of(user)));

        ResponseEntity<User> response = userService.getUserById(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(user);
    }

    @Test
    void testCreateUser() {
        User user = new User("John", "Manager", "john", "1234");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertThat(result).isEqualTo(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        User user = new User();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);

        ResponseEntity<User> response = userService.updateUser(userId, user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(user);
        verify(userRepository, times(1)).existsById(1L);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser(){
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        ResponseEntity<Void> response = userService.deleteUser(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
