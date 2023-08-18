package com.example.UserService.controller;

import com.example.UserService.entities.User;
import com.example.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    //create

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User user1= userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){

      User user=userService.getUser(userId);

      return new ResponseEntity<>(user,HttpStatus.OK);

    }

    //single user get


    //all user get
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){

       List<User>  allUser=userService.getAllUser();

       return ResponseEntity.ok(allUser);
    }



}
