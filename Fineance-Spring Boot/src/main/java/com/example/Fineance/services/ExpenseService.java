package com.example.Fineance.services;

import com.example.Fineance.dto.CategorySummaryDTO;
import com.example.Fineance.models.Expense;
import com.example.Fineance.models.User;
import com.example.Fineance.repositories.ExpenseRepository;
import com.example.Fineance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public List<Expense> getAllExpensesByUser(long id_user) {
        Optional<User> user = userRepository.findById(id_user);
        return expenseRepository.findByUser(user);
    }

    public List<Expense> getExpensesByTitle(String title) {
        return expenseRepository.findByTitle(title);
    }

    public List<CategorySummaryDTO> getTopExpenseCategories(long id_user, int count) {
        return expenseRepository.findTopExpenseCategories(id_user, PageRequest.of(0, count));
    }

    public Expense addExpense(Expense expense) {
        expenseRepository.save(expense);
        return expense;
    }

    public List<Expense> searchExpenses(long id_user, String title) {
        return expenseRepository.searchExpenses(id_user, title);
    }

    public Optional<Expense> getExpenseById(long id) {
        return expenseRepository.findById(id);
    }
}
