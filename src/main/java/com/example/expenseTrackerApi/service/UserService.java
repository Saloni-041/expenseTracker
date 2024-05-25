package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.entity.UserModel;

public interface UserService {
     User createUser(UserModel userModel);
     User readUser(Long id);
     User updateUser(UserModel user,Long id);
     void delete(Long id);
}
