package com.example.Fineance.services;

import com.example.Fineance.models.Income;
import com.example.Fineance.repositories.IncomeRepository;
import com.example.Fineance.repositories.UserRepository;
import com.example.Fineance.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IncomeServiceTest {
    @Mock
    private IncomeRepository incomeRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private IncomeService incomeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllIncomesByUser_shouldReturnList() {
        User user = new User();
        Income income = new Income();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(incomeRepository.findByUser(Optional.of(user))).thenReturn(Arrays.asList(income));
        List<Income> result = incomeService.getAllIncomesByUser(1L);
        assertEquals(1, result.size());
    }

    @Test
    void getAllIncomesByUser_shouldReturnEmptyList() {
        when(userRepository.findById(2L)).thenReturn(Optional.of(new User()));
        when(incomeRepository.findByUser(Optional.of(new User()))).thenReturn(Collections.emptyList());
        List<Income> result = incomeService.getAllIncomesByUser(2L);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllIncomesByUser_shouldReturnEmptyOptionalIfUserNotFound() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());
        List<Income> result = incomeService.getAllIncomesByUser(3L);
        assertTrue(result.isEmpty());
    }

    @Test
    void addIncome_shouldReturnIncome() {
        Income income = new Income();
        when(incomeRepository.save(income)).thenReturn(income);
        Income result = incomeService.addIncome(income);
        assertNotNull(result);
    }

    @Test
    void getIncomeById_shouldReturnIncome() {
        Income income = new Income();
        when(incomeRepository.findById(1L)).thenReturn(Optional.of(income));
        Optional<Income> result = incomeService.getIncomeById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void getIncomeById_shouldReturnEmpty() {
        when(incomeRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Income> result = incomeService.getIncomeById(2L);
        assertFalse(result.isPresent());
    }
}
