package com.example.Fineance.controllers;

import com.example.Fineance.models.Expense;
import com.example.Fineance.models.Income;
import com.example.Fineance.services.ExpenseService;
import com.example.Fineance.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users/{id_user}/operations")
public class OperationController {
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @Autowired
    public OperationController(ExpenseService expenseService, IncomeService incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpensesByUser(@PathVariable Long id_user) {
        return expenseService.getAllExpensesByUser(id_user);
    }

    @GetMapping("/incomes")
    public List<Income> getAllIncomesByUser(@PathVariable Long id_user) {
        return incomeService.getAllIncomesByUser(id_user);
    }
}
