package com.example.Fineance.controllers;

import com.example.Fineance.dto.AddOperationDTO;
import com.example.Fineance.models.Expense;
import com.example.Fineance.models.Income;
import com.example.Fineance.models.User;
import com.example.Fineance.repositories.UserRepository;
import com.example.Fineance.services.ExpenseService;
import com.example.Fineance.services.IncomeService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(OperationController.class)
class OperationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExpenseService expenseService;
    @MockBean
    private IncomeService incomeService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private com.example.Fineance.services.JwtService jwtService;
    @MockBean
    private com.example.Fineance.security.JwtFilter jwtFilter;

    @Test
    void addIncome_shouldReturnOk() throws Exception {
        User user = new User();
        user.setId_user(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(incomeService.addIncome(Mockito.any())).thenReturn(new Income());
        mockMvc.perform(post("/api/operations/addIncome")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_user\":1,\"title\":\"Test\",\"amount\":100,\"date\":\"2024-01-01\",\"category\":\"Test\"}")
                .with(csrf())
                .with(user("test").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void addExpense_shouldReturnOk() throws Exception {
        User user = new User();
        user.setId_user(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(expenseService.addExpense(Mockito.any())).thenReturn(new Expense());
        mockMvc.perform(post("/api/operations/addExpense")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id_user\":1,\"title\":\"Test\",\"amount\":100,\"date\":\"2024-01-01\",\"category\":\"Test\"}")
                .with(csrf())
                .with(user("test").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    void getHistory_shouldReturnOk() throws Exception {
        mockMvc.perform(get("/api/operations/history?id_user=1")
                .with(user("test").roles("USER")))
                .andExpect(status().isOk());
    }
}

