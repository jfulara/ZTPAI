package com.example.Fineance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddOperationDTO {
    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private String category;
    private Long id_user;

    public AddOperationDTO(String title, BigDecimal amount, LocalDate date, String category, Long id_user) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.id_user = id_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }
}
