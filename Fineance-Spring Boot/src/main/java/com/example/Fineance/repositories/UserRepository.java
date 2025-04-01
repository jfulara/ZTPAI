package com.example.Fineance.repositories;

import com.example.Fineance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByEmail(String email);
    User findById(long id);
}
