package com.example.expenseTrackerApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //will inc id by 1
    private Long id;

    @Column(name="expense_name")
    private String name;

    private String description;

    @Column(name="expense_amount")
    private BigDecimal amount;

    private String category;

    private Date date;

    @Column(name="created_time",nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name="lastmodifiedtime")
    @UpdateTimestamp
    private Timestamp updatedTime;

}
