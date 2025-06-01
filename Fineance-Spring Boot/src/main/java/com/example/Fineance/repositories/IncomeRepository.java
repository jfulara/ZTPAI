package com.example.Fineance.repositories;

import com.example.Fineance.dto.CategorySummaryDTO;
import com.example.Fineance.models.Income;
import com.example.Fineance.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findAll();
    List<Income> findByUser(Optional<User> user);
    List<Income> findByTitle(String title);

    @Query("SELECT new com.example.Fineance.dto.CategorySummaryDTO(i.category, SUM(i.amount)) " +
            "FROM Income i WHERE i.user.id_user = :id_user GROUP BY i.category ORDER BY SUM(i.amount) DESC")
    List<CategorySummaryDTO> findTopIncomeCategories(long id_user, Pageable pageable);
}
