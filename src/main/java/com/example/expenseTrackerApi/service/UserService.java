package com.example.expenseTrackerApi.service;

import com.example.expenseTrackerApi.entity.User;
import com.example.expenseTrackerApi.entity.UserModel;

public interface UserService {
    //we do not add access modifier(public) here as all the methods that are added in interface are by default public
    User createUser(UserModel userModel);

    User readUser();

    User updateUser(UserModel user);

    void delete();

    User getLoggedInUser();

}
