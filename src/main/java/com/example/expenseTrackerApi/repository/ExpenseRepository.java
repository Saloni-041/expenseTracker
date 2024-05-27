package com.example.expenseTrackerApi.repository;

import com.example.expenseTrackerApi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>
{
    //all these are data jpa finder/query methods
    // select * from tbl_expenses where usr_id=? and category=?
    public Page<Expense> findByUserIdAndCategory(Long userId,String category, Pageable page);

    // select * from tbl_expenses where user_id=? and name like '%keyword%'
    // here containing keyword is used to apply like clause
    public Page<Expense> findByUserIdAndNameContaining(Long userId,String name,Pageable page);

    // select * from tbl_expenses where user_id=? and date between 'startdate' and 'enddate'
    public Page<Expense> findByUserIdAndDateBetween(Long userId,Date startDate,Date endDate,Pageable page);

    //select * from tbl_expenses where user_id=?
    Page<Expense> findByUserId(Long userId,Pageable page);

    //select * from tbl_expenses where user_id=? and id=?
    Optional<Expense> findByUserIdAndId(Long userId, Long expenseId);
}
