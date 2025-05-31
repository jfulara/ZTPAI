package com.example.Fineance.controllers;

import com.example.Fineance.services.ExpenseService;
import com.example.Fineance.services.IncomeService;
import com.example.Fineance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    private final UserService userService;

    private final IncomeService incomeService;

    private final ExpenseService expenseService;

    @Autowired
    public HomeController(UserService userService, IncomeService incomeService, ExpenseService expenseService) {
        this.userService = userService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }

    @GetMapping("/home")
    public String home() {

    }
}
