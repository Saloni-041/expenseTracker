package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.entity.UserModel;
import com.example.expenseTrackerApi.exceptions.ItemAlreadyExistsException;
import com.example.expenseTrackerApi.exceptions.ResourceNotFoundException;
import com.example.expenseTrackerApi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpel implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {

        if(userRepository.existsByEmail(userModel.getEmail()))
            throw new ItemAlreadyExistsException("User is already registered with email:"+userModel.getEmail());
        User newUser=new User();
        BeanUtils.copyProperties(userModel,newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found with id:"+id));
    }

    @Override
    public User updateUser(UserModel user, Long id) {
        User exisitingUser=readUser(id);
        exisitingUser.setName(user.getName()!=null? user.getName() : exisitingUser.getName());
        exisitingUser.setEmail(user.getEmail()!=null? user.getEmail() : exisitingUser.getEmail());
        exisitingUser.setAge(user.getAge()!=null? user.getAge() : exisitingUser.getAge());
        exisitingUser.setPassword(user.getPassword()!=null? user.getPassword() : exisitingUser.getPassword());
        return userRepository.save(exisitingUser);
    }

    @Override
    public void delete(Long id) {
        User exisitingUser=readUser(id);
        userRepository.delete(exisitingUser);
    }
}
