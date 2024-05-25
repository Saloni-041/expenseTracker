package com.example.expenseTrackerApi.repository;

import com.example.expenseTrackerApi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>
{
    //all these are data jpa finder/query methods
    // select * from tbl_expenses where category=?
    public Page<Expense> findByCategory(String category, Pageable page);

    // select * from tbl_expenses where name like '%keyword%'
    // here containing keyword is used to apply like clause
    public Page<Expense> findByNameContaining(String name,Pageable page);

    // select * from tbl_expenses where date between 'startdate' and 'enddate'
    public Page<Expense> findByDateBetween(Date startDate,Date endDate,Pageable page);
}
