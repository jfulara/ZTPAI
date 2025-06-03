package com.example.Fineance.services;

import com.example.Fineance.models.Token;
import com.example.Fineance.models.User;
import com.example.Fineance.repositories.TokenRepository;
import com.example.Fineance.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenServiceTest {
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteTokenByValue_shouldCallRepository() {
        tokenService.deleteTokenByValue("abc");
        verify(tokenRepository, times(1)).deleteByToken("abc");
    }

    @Test
    void saveToken_shouldSaveTokenWithCorrectValues() {
        User user = new User();
        String tokenValue = "abc";
        tokenService.saveToken(tokenValue, user);
        verify(tokenRepository, times(1)).save(argThat(token ->
                token.getToken().equals(tokenValue) &&
                token.getUser() == user &&
                !token.isRevoked() &&
                !token.isExpired()
        ));
    }

    @Test
    void deleteTokenByUserEmail_shouldDeleteIfUserExists() {
        User user = new User();
        when(userRepository.findByEmail("mail@mail.com")).thenReturn(Optional.of(user));
        tokenService.deleteTokenByUserEmail("mail@mail.com");
        verify(tokenRepository, times(1)).deleteByUser(user);
    }

    @Test
    void deleteTokenByUserEmail_shouldNotDeleteIfUserNotExists() {
        when(userRepository.findByEmail("mail@mail.com")).thenReturn(Optional.empty());
        tokenService.deleteTokenByUserEmail("mail@mail.com");
        verify(tokenRepository, never()).deleteByUser(any());
    }
}
