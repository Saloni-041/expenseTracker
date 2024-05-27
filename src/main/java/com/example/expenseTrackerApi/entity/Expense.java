package com.example.expenseTrackerApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

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
    @Size(min=3 ,message = "Expense name must be atleast three characters")
    @NotBlank(message = "Expense name cannot be null,empty or blank")
    private String name;

    private String description;

    @Column(name="expense_amount")
    @NotNull(message = "Expense amount is necessary field")
    private BigDecimal amount;

    @NotBlank(message = "Expense category cannot be null,empty or blank")
    private String category;

    @NotNull(message="Date cannot be null")
    //here Date is of sql package not util package
    private Date date;

    @Column(name="created_time",nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name="lastmodifiedtime")
    @UpdateTimestamp
    private Timestamp updatedTime;

    //The FetchType.LAZY strategy is used to optimize performance by deferring the loading of the associated entity until it is actually needed.
    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JoinColumn(name="user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)    //when user is deleted expenses should also be deleted
    @JsonIgnore                                  //when we are fetching expense we are not going to fetch user
    private User user;

}
