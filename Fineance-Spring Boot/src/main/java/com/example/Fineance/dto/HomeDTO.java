package com.example.Fineance.dto;

import com.example.Fineance.models.Expense;
import com.example.Fineance.models.Income;

import java.util.List;

public class HomeDTO {
    private List<Income> incomes;
    private List<Expense> expenses;
    private double totalIncome;
    private double totalExpense;
    private List<CategorySummaryDTO> incomeCategorySummaries;
    private List<CategorySummaryDTO> expenseCategorySummaries;

    public HomeDTO(List<Income> incomes, List<Expense> expenses, double totalIncome, double totalExpense,
                   List<CategorySummaryDTO> incomeCategorySummaries, List<CategorySummaryDTO> expenseCategorySummaries) {
        this.incomes = incomes;
        this.expenses = expenses;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.incomeCategorySummaries = incomeCategorySummaries;
        this.expenseCategorySummaries = expenseCategorySummaries;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public List<CategorySummaryDTO> getIncomeCategorySummaries() {
        return incomeCategorySummaries;
    }

    public void setIncomeCategorySummaries(List<CategorySummaryDTO> incomeCategorySummaries) {
        this.incomeCategorySummaries = incomeCategorySummaries;
    }

    public List<CategorySummaryDTO> getExpenseCategorySummaries() {
        return expenseCategorySummaries;
    }

    public void setExpenseCategorySummaries(List<CategorySummaryDTO> expenseCategorySummaries) {
        this.expenseCategorySummaries = expenseCategorySummaries;
    }
}
