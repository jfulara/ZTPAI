package com.example.Fineance.services;

import com.example.Fineance.models.User;
import com.example.Fineance.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnUserList() {
        User user = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<User> result = userService.getAllUsers();
        assertEquals(1, result.size());
    }

    @Test
    void getAllUsers_shouldReturnEmptyList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        List<User> result = userService.getAllUsers();
        assertTrue(result.isEmpty());
    }

    @Test
    void getUserById_shouldReturnUser() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void getUserById_shouldReturnEmpty() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<User> result = userService.getUserById(2L);
        assertFalse(result.isPresent());
    }

    @Test
    void deleteUserById_shouldReturnTrue() {
        User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        boolean result = userService.deleteUserById(1L);
        assertTrue(result);
    }

    @Test
    void deleteUserById_shouldReturnFalse() {
        when(userRepository.existsById(2L)).thenReturn(false);
        boolean result = userService.deleteUserById(2L);
        assertFalse(result);
    }
}

