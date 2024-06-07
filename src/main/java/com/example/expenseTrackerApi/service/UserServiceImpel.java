package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.entity.UserModel;
import com.example.expenseTrackerApi.exceptions.ItemAlreadyExistsException;
import com.example.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.example.expenseTrackerApi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpel implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {

        if (userRepository.existsByEmail(userModel.getEmail()))
            throw new ItemAlreadyExistsException("User is already registered with email:" + userModel.getEmail());
        User newUser = new User();
        BeanUtils.copyProperties(userModel, newUser);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User readUser() {
        Long id = getLoggedInUser().getId();
        return userRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
    }

    @Override
    public User updateUser(UserModel user) {
        User exisitingUser = readUser();
        exisitingUser.setName(user.getName() != null ? user.getName() : exisitingUser.getName());
        exisitingUser.setEmail(user.getEmail() != null ? user.getEmail() : exisitingUser.getEmail());
        exisitingUser.setAge(user.getAge() != null ? user.getAge() : exisitingUser.getAge());
        exisitingUser.setPassword(user.getPassword() != null ? user.getPassword() : exisitingUser.getPassword());
        return userRepository.save(exisitingUser);
    }

    @Override
    public void delete() {
        User exisitingUser = readUser();
        userRepository.delete(exisitingUser);
    }

    @Override
    public User getLoggedInUser() {
        //securitycontextholder have authentication object when we logged in, so we are using that only
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user exists with email" + email));
    }
}
