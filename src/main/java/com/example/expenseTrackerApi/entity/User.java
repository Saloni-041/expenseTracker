package com.example.expenseTrackerApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "tbl_users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    @Email(message = "Please enter valid email")
    private String email;

    @JsonIgnore
    //When you return the User object from your controller, Jackson will serialize it to JSON. Due to the @JsonIgnore annotation on the password field, the password will not be included in the JSON response sent back to the client.
    private String password;

    private Long age;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    //here we have used Date as sql timestamp
    private Date created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    //here we have used Date as sql timestamp
    private Date updated_at;
}
