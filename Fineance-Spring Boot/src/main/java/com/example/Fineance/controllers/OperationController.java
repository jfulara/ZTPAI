package com.example.Fineance.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/operations")
public class OperationController {
    private static final Map<Integer, Map<String, String>> expenses = new
            HashMap<>() {{
                put(1, Map.of("id", "1", "id_user", "1", "title", "Taxi", "amount",
                        "20", "date", "2025-02-20", "category", "Transport"));
                put(2, Map.of("id", "2", "id_user", "1", "title", "Zakupy", "amount",
                        "120", "date", "2025-02-23", "category", "Codzienne wydatki"));
            }};

    @GetMapping("/expenses")
    public ResponseEntity<Object> getExpenses(){
        return ResponseEntity.ok(expenses.values());
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Object> getExpenseById(@PathVariable int id){
        if (!expenses.containsKey(id)){
            return ResponseEntity.status(404).body(Map.of("error", "Expense not found"));
        }
        return ResponseEntity.ok(expenses.get(id));
    }

    private static final Map<Integer, Map<String, String>> incomes = new
            HashMap<>() {{
                put(1, Map.of("id", "1", "id_user", "1", "title", "Wyplata", "amount",
                        "2000", "date", "2025-02-10", "category", "Wynagrodzenie"));
                put(2, Map.of("id", "2", "id_user", "1", "title", "Prezent", "amount",
                        "1000", "date", "2025-02-15", "category", "Podarunek"));
            }};

    @GetMapping("/incomes")
    public ResponseEntity<Object> getIncomes(){
        return ResponseEntity.ok(incomes.values());
    }

    @GetMapping("/incomes/{id}")
    public ResponseEntity<Object> getIncomeById(@PathVariable int id){
        if (!incomes.containsKey(id)){
            return ResponseEntity.status(404).body(Map.of("error", "Income not found"));
        }
        return ResponseEntity.ok(incomes.get(id));
    }
}
