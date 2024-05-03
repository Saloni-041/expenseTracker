package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.entity.Expense;
import com.example.expenseTrackerApi.service.ExpenseServiceImpel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpel expenseServiceImpel;

    @GetMapping("/expenses")
    public List<Expense> getExpenses(Pageable page)
    {
        return expenseServiceImpel.getAllExpenses(page).toList();
    }

    @GetMapping("/expense/{id}")
    public Expense getExpenseById(@PathVariable Long id)
    {
        return expenseServiceImpel.getExpenseById(id);
    }

    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    @DeleteMapping("/expense")
    public void deleteExpenseById(@RequestParam Long id)
    {
        expenseServiceImpel.deleteExpenseById(id);
    }

    @ResponseStatus(value= HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpense(@RequestBody Expense expense)
    {
        return expenseServiceImpel.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable Long id,@RequestBody Expense expense)
    {
        return expenseServiceImpel.updateExpenseDetails(id,expense);
    }
}
