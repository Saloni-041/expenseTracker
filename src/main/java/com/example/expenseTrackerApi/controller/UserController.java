package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.entity.UserModel;
import com.example.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.example.expenseTrackerApi.service.UserServiceImpel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpel userServiceImpel;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel userModel)
    {
        return new ResponseEntity<>(userServiceImpel.createUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> readUser(@PathVariable Long id)
    {
        return new ResponseEntity<>(userServiceImpel.readUser(id), HttpStatus.OK);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserModel userModel,@PathVariable Long id)
    {
        return new ResponseEntity<User>(userServiceImpel.updateUser(userModel,id),HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) throws ResourceNotFoundException
    {
        userServiceImpel.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
