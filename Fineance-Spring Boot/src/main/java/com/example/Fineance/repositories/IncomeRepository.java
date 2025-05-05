package com.example.Fineance.repositories;

import com.example.Fineance.models.Income;
import com.example.Fineance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findAll();
    List<Income> findByUser(Optional<User> user);
    List<Income> findByTitle(String title);
}
