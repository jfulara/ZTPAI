package com.example.Fineance.services;

import com.example.Fineance.models.User;
import com.example.Fineance.repositories.UserRepository;
import com.example.Fineance.dto.RegisterRequest;
import com.example.Fineance.dto.AuthRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MessageSender messageSender;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldSaveUser() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setName("Test");
        request.setSurname("User");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        authService.register(request);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void authenticate_shouldThrowExceptionIfUserNotFound() {
        AuthRequest request = new AuthRequest();
        request.setEmail("notfound@example.com");
        request.setPassword("password");
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> authService.authenticate(request));
    }
}

