package com.example.Fineance.services;

import com.example.Fineance.models.Expense;
import com.example.Fineance.repositories.ExpenseRepository;
import com.example.Fineance.repositories.UserRepository;
import com.example.Fineance.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllExpensesByUser_shouldReturnList() {
        User user = new User();
        Expense expense = new Expense();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(expenseRepository.findByUser(Optional.of(user))).thenReturn(Arrays.asList(expense));
        List<Expense> result = expenseService.getAllExpensesByUser(1L);
        assertEquals(1, result.size());
    }

    @Test
    void getAllExpensesByUser_shouldReturnEmptyList() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(new User()));
        when(expenseRepository.findByUser(Optional.of(new User()))).thenReturn(Collections.emptyList());
        List<Expense> result = expenseService.getAllExpensesByUser(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    void addExpense_shouldReturnExpense() {
        Expense expense = new Expense();
        when(expenseRepository.save(expense)).thenReturn(expense);
        Expense result = expenseService.addExpense(expense);
        assertNotNull(result);
    }

    @Test
    void getExpenseById_shouldReturnExpense() {
        Expense expense = new Expense();
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(expense));
        Optional<Expense> result = expenseService.getExpenseById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void getExpenseById_shouldReturnEmpty() {
        when(expenseRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Expense> result = expenseService.getExpenseById(2L);
        assertFalse(result.isPresent());
    }
}

