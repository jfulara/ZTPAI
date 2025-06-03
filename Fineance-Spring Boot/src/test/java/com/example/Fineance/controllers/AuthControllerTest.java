package com.example.Fineance.controllers;

import com.example.Fineance.dto.AuthRequest;
import com.example.Fineance.dto.RegisterRequest;
import com.example.Fineance.dto.UserDTO;
import com.example.Fineance.models.User;
import com.example.Fineance.services.AuthService;
import com.example.Fineance.services.JwtService;
import com.example.Fineance.services.TokenService;
import com.example.Fineance.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private TokenService tokenService;
    @MockBean
    private com.example.Fineance.security.JwtFilter jwtFilter;
    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    private UserRepository userRepository;

    @Test
    void login_shouldReturnOk() throws Exception {
        AuthRequest request = new AuthRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        User user = new User();
        user.setId_user(1L);
        user.setEmail("test@example.com");
        Mockito.when(authService.authenticate(Mockito.any())).thenReturn(user);
        Mockito.when(jwtService.generateAccessToken(Mockito.any())).thenReturn("access");
        Mockito.when(jwtService.generateRefreshToken(Mockito.any())).thenReturn("refresh");
        mockMvc.perform(post("/api/auth/login")
                .with(csrf())
                .with(user("test@example.com"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void register_shouldReturnOk() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setName("Test");
        request.setSurname("User");
        mockMvc.perform(post("/api/auth/register")
                .with(csrf())
                .with(user("test@example.com"))
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password\",\"name\":\"Test\",\"surname\":\"User\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void logout_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/auth/logout")
                .with(csrf())
                .with(user("test@example.com")))
                .andExpect(status().isOk());
    }
}

