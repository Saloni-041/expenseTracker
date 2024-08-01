package com.example.expenseTrackerApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role_Table
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String roleName;

//  mappedBy = "roles" indicates that the User entity owns the relationship, and Role_Table does not need to specify the join table details. It merely references the roles field in the User entity.
    @ManyToMany(mappedBy = "roles")
//  In bidirectional relationships, such as many-to-many, you can end up with circular references (e.g., User referencing Role_Table and vice versa). This can lead to infinite loops during serialization. @JsonIgnore helps to prevent this by ignoring one side of the relationship.
    @JsonIgnore
//  It specifies a many-to-many relationship where one Role_Table can be assigned to multiple User instances.
    private List<User> users;
}
