package com.example.Fineance.services;

import com.example.Fineance.models.Expense;
import com.example.Fineance.models.User;
import com.example.Fineance.repositories.ExpenseRepository;
import com.example.Fineance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
        User user = userRepository.findById(id_user);
        return expenseRepository.findByUser(user);
    }

    public List<Expense> getExpensesByTitle(String title) {
        return expenseRepository.findByTitle(title);
    }
}
