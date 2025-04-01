package com.example.Fineance.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_income;

    private String title;
    @Column(precision = 19, scale = 2)
    private BigDecimal amount;
    private LocalDate date;
    private String category;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
