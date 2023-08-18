package com.example.UserService.services;

import com.example.UserService.entities.User;

import java.util.List;

public interface UserService {

    //user operations

    //create
    User saveUser(User user);


    //get all users
    List<User> getAllUser();


    //get single user of given userID

    User getUser(String userID);


}
