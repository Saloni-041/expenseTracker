package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.entity.Expense;
import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.example.expenseTrackerApi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpel implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(),page);
//        return expenseRepository.findAll(page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> obj=expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(),id);
//        Optional<Expense> obj=expenseRepository.findById(id);
        if(obj.isPresent())
            return obj.get();
        throw new ResourceNotFoundException("Expense is not found for id "+id);
    }

    @Override
    public void deleteExpenseById(Long id) {
        Expense expense=getExpenseById(id);
        expenseRepository.deleteById(id);
    }

    @Override
    public Expense saveExpenseDetails(Expense expense) {
        User user=userService.getLoggedInUser();
        expense.setUser(user);
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

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category,page).toList();
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword,page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
        if(startDate==null)
        {
            //take first record start date
            startDate=new Date(0);
        }
        if(endDate==null)
        {
            endDate=new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),startDate,endDate,page).toList();
    }

}
