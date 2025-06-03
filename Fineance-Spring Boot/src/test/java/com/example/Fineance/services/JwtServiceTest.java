package com.example.Fineance.services;

import com.example.Fineance.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {
    @InjectMocks
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Key testKey = Keys.hmacShaKeyFor("testtesttesttesttesttesttesttest".getBytes());
        ReflectionTestUtils.setField(jwtService, "signingKey", testKey);
    }

    @Test
    void generateAccessToken_shouldReturnToken() {
        User user = new User();
        user.setEmail("test@example.com");
        String token = jwtService.generateAccessToken(user);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void generateRefreshToken_shouldReturnToken() {
        User user = new User();
        user.setEmail("test@example.com");
        String token = jwtService.generateRefreshToken(user);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
}

