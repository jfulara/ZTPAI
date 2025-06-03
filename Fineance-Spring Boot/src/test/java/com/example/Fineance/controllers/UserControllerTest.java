package com.example.Fineance.controllers;

import com.example.Fineance.models.User;
import com.example.Fineance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private com.example.Fineance.services.JwtService jwtService;
    @MockBean
    private com.example.Fineance.security.JwtFilter jwtFilter;

    @Test
    void getAllUsers_shouldReturnOk() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/users").with(user("test@example.com")))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById_shouldReturnOk() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenReturn(Optional.of(new User()));
        mockMvc.perform(get("/api/users/1").with(user("test@example.com")))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser_shouldReturnOk() throws Exception {
        Mockito.when(userService.deleteUserById(1L)).thenReturn(true);
        mockMvc.perform(delete("/api/users/1").with(user("test@example.com").roles("ADMIN")).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser_shouldReturnNotFound() throws Exception {
        // Najpierw mockujemy 1L na true, potem 2L na false
        Mockito.when(userService.deleteUserById(1L)).thenReturn(true);
        Mockito.when(userService.deleteUserById(2L)).thenReturn(false);
        mockMvc.perform(delete("/api/users/2").with(user("test@example.com").roles("ADMIN")).with(csrf()))
                .andExpect(status().isNotFound());
        Mockito.verify(userService).deleteUserById(2L);
    }
}
