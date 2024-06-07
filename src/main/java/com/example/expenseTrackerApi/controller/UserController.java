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

//    @PostMapping("/register")
//    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel userModel)
//    {
//        return new ResponseEntity<>(userServiceImpel.createUser(userModel), HttpStatus.CREATED);
//    }

    @GetMapping("/profile")
    public ResponseEntity<User> readUser() {
        return new ResponseEntity<>(userServiceImpel.readUser(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel userModel) {
        return new ResponseEntity<User>(userServiceImpel.updateUser(userModel), HttpStatus.OK);
    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser() throws ResourceNotFoundException {
        userServiceImpel.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
