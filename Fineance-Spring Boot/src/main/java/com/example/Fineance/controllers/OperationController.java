package com.example.Fineance.controllers;

import com.example.Fineance.dto.AddOperationDTO;
import com.example.Fineance.dto.OperationDTO;
import com.example.Fineance.models.Expense;
import com.example.Fineance.models.Income;
import com.example.Fineance.models.User;
import com.example.Fineance.repositories.UserRepository;
import com.example.Fineance.services.ExpenseService;
import com.example.Fineance.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final UserRepository userRepository;

    @Autowired
    public OperationController(ExpenseService expenseService, IncomeService incomeService, UserRepository userRepository) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpensesByUser(@PathVariable Long id_user) {
        return expenseService.getAllExpensesByUser(id_user);
    }

    @GetMapping("/incomes")
    public List<Income> getAllIncomesByUser(@PathVariable Long id_user) {
        return incomeService.getAllIncomesByUser(id_user);
    }

    @PostMapping("/addIncome")
    public ResponseEntity<Income> addIncome(@RequestBody AddOperationDTO addIncome) {
        Optional<User> user = userRepository.findById(addIncome.getId_user());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Income income = new Income();
        income.setTitle(addIncome.getTitle());
        income.setAmount(addIncome.getAmount());
        income.setDate(addIncome.getDate());
        income.setCategory(addIncome.getCategory());
        income.setUser(user.get());
        Income savedIncome = incomeService.addIncome(income);
        if (savedIncome == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(savedIncome);
    }

    @PostMapping("/addExpense")
    public ResponseEntity<Expense> addExpense(@RequestBody AddOperationDTO addExpense) {
        Optional<User> user = userRepository.findById(addExpense.getId_user());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Expense expense = new Expense();
        expense.setTitle(addExpense.getTitle());
        expense.setAmount(addExpense.getAmount());
        expense.setDate(addExpense.getDate());
        expense.setCategory(addExpense.getCategory());
        expense.setUser(user.get());
        Expense savedExpense = expenseService.addExpense(expense);
        if (savedExpense == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(savedExpense);
    }

    @GetMapping("/history")
    public ResponseEntity<List<OperationDTO>> searchOperations(
            @RequestParam Long id_user,
            @RequestParam(required = false, defaultValue = "") String title
    ) {
        List<Expense> expenses = expenseService.searchExpenses(id_user, title);
        List<Income> incomes = incomeService.searchIncomes(id_user, title);
        List<OperationDTO> result = new ArrayList<>();
        for (Expense e : expenses) {
            result.add(new OperationDTO(
                    e.getId_expense(),
                    e.getTitle(),
                    e.getAmount().negate(),
                    e.getDate(),
                    e.getCategory(),
                    "EXPENSE"
            ));
        }
        for (Income i : incomes) {
            result.add(new OperationDTO(
                    i.getId_income(),
                    i.getTitle(),
                    i.getAmount(),
                    i.getDate(),
                    i.getCategory(),
                    "INCOME"
            ));
        }
        List<OperationDTO> filtered = result.stream()
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filtered);
    }
}
