package com.example.Fineance.controllers;

import com.example.Fineance.dto.CategorySummaryDTO;
import com.example.Fineance.dto.HomeDTO;
import com.example.Fineance.models.Expense;
import com.example.Fineance.models.Income;
import com.example.Fineance.models.User;
import com.example.Fineance.services.ExpenseService;
import com.example.Fineance.services.IncomeService;
import com.example.Fineance.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@WebMvcTest(HomeController.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private IncomeService incomeService;
    @MockBean
    private ExpenseService expenseService;
    @MockBean
    private com.example.Fineance.services.JwtService jwtService;
    @MockBean
    private com.example.Fineance.security.JwtFilter jwtFilter;

    @Test
    void getHomeInfo_shouldReturnOk() throws Exception {
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn("test@example.com");
        User user = new User();
        user.setId_user(1L);
        Mockito.when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));
        Mockito.when(incomeService.getAllIncomesByUser(1L)).thenReturn(Collections.emptyList());
        Mockito.when(expenseService.getAllExpensesByUser(1L)).thenReturn(Collections.emptyList());
        Mockito.when(incomeService.getTopIncomeCategories(1L, 4)).thenReturn(Collections.emptyList());
        Mockito.when(expenseService.getTopExpenseCategories(1L, 4)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/api/home")
                .with(user("test@example.com")))
                .andExpect(status().isOk());
    }
}

