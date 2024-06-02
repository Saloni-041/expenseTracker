package com.example.expenseTrackerApi.controller;

import com.example.expenseTrackerApi.entity.AuthModel;
import com.example.expenseTrackerApi.entity.JwtResponse;
import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.entity.UserModel;
import com.example.expenseTrackerApi.security.CustomUserDetailsService;
import com.example.expenseTrackerApi.service.UserServiceImpel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.expenseTrackerApi.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserServiceImpel userServiceImpel;

    //it is an interface which provides method authenticate
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception {
        authenticate(authModel.getEmail(), authModel.getPassword());

        //we need to generate JWT Token we need to get userdetails first then pass it to jwt paylod
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authModel.getEmail());
        System.out.println(userDetails.toString());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println("authentication recieved " + authentication.toString());
        } catch (DisabledException e) {
            throw new Exception("User disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserModel userModel) {
        return new ResponseEntity<User>(userServiceImpel.createUser(userModel), HttpStatus.CREATED);
    }
}
