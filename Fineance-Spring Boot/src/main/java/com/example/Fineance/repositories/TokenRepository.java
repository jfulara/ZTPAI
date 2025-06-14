package com.example.Fineance.repositories;

import com.example.Fineance.models.Token;
import com.example.Fineance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("""
        SELECT t FROM Token t WHERE t.user.id_user = :id_user AND (t.expired = false AND t.revoked = false)
    """)
    List<Token> findAllValidTokensByUser(Long id_user);
    Optional<Token> findByToken(String token);
    void deleteByUser(User user);
    void deleteByToken(String token);
}
