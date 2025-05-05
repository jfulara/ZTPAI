package com.example.Fineance.services;

import com.example.Fineance.models.Income;
import com.example.Fineance.models.User;
import com.example.Fineance.repositories.IncomeRepository;
import com.example.Fineance.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository, UserRepository userRepository) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    public List<Income> getAllIncomesByUser(long id_user) {
        Optional<User> user = userRepository.findById(id_user);
        return incomeRepository.findByUser(user);
    }

    public List<Income> getIncomesByTitle(String title) {
        return incomeRepository.findByTitle(title);
    }
}
