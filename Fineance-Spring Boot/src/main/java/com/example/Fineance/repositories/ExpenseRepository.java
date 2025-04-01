package com.example.Fineance.repositories;

import com.example.Fineance.models.Expense;
import com.example.Fineance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAll();
    List<Expense> findByUser(User user);
    List<Expense> findByTitle(String title);
}
