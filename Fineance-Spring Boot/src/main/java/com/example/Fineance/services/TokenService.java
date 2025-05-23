package com.example.Fineance.services;

import com.example.Fineance.models.User;
import com.example.Fineance.repositories.TokenRepository;
import com.example.Fineance.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    public TokenService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public void deleteTokenByUserEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        userOpt.ifPresent(tokenRepository::deleteByUser);
    }
}
