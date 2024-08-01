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
import java.util.ArrayList;
import java.util.List;

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
    //When you return the User object from your controller, Jackson will serialize it to JSON.
    // Due to the @JsonIgnore annotation on the password field, the password will not be
    // included in the JSON response sent back to the client.
    private String password;

    private Long age;

    @ManyToMany
    @JoinTable(name="user_roles",
//  If your User entity has an id column as its primary key, then user_id in the user_roles table will store values that correspond to the id values of the User entity.
            joinColumns = @JoinColumn(name="user_id"),
//  Specifies the foreign key column in the join table that references the primary key of the entity in the opposite class.
            inverseJoinColumns = @JoinColumn(name="role_id"))
//  It specifies a many-to-many relationship where one User can have multiple Role_Table instances associated with it.
    private List<Role_Table> roles;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    //here we have used Date as sql timestamp
    private Date created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    //here we have used Date as sql timestamp
    private Date updated_at;
}
