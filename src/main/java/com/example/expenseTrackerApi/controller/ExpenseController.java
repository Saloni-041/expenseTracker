package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.entity.Expense;
import com.example.expenseTrackerApi.service.ExpenseServiceImpel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpel expenseServiceImpel;

    @GetMapping("/expenses")
    public List<Expense> getExpenses(Pageable page) {
        return expenseServiceImpel.getAllExpenses(page).toList();
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable Long id) {
        return expenseServiceImpel.getExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expense")
    public void deleteExpenseById(@RequestParam Long id) {
        expenseServiceImpel.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpense(@Valid @RequestBody Expense expense) {
        return expenseServiceImpel.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseServiceImpel.updateExpenseDetails(id, expense);
    }

    @GetMapping("/expense/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseServiceImpel.readByCategory(category, page);
    }

    @GetMapping("/expense/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page) {
        return expenseServiceImpel.readByName(keyword, page);
    }

    @GetMapping("/expense/date")
    public List<Expense> getExpensesByDate(@RequestParam(required = false) Date startDate, @RequestParam(required = false) Date endDate, Pageable page) {
        return expenseServiceImpel.readByDate(startDate, endDate, page);
    }
}
