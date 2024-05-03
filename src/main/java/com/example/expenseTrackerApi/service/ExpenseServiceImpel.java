package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.entity.Expense;
import com.example.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.example.expenseTrackerApi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpel implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> obj=expenseRepository.findById(id);
        if(obj.isPresent())
            return obj.get();
        throw new ResourceNotFoundException("Expense is not found for id "+id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpenseDetails(Long id, Expense expense) {
        Expense existingExpense=getExpenseById(id);
         existingExpense.setName(expense.getName()!=null ? expense.getName() : existingExpense.getName());
         existingExpense.setDescription(expense.getDescription()!=null ? expense.getDescription() : existingExpense.getDescription());
         existingExpense.setAmount(expense.getAmount()!=null ? expense.getAmount() : existingExpense.getAmount());
         existingExpense.setCategory(expense.getCategory()!=null ? expense.getCategory() : existingExpense.getCategory());
         existingExpense.setDate(expense.getDate()!=null ? expense.getDate() : existingExpense.getDate());
         expenseRepository.save(existingExpense);
         return existingExpense;
    }
}
