package com.example.reacthree.controllers;


import com.example.reacthree.entity.User;
import com.example.reacthree.services.userService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class userController {


    @Autowired
    private userService userService;



    @GetMapping()
    public List<User> getAll()
    {
        return userService.getAllusers();
    }

    @GetMapping("/name/{userName}")
    public User getUserName(@PathVariable String userName)
    {
        return userService.getUserByName(userName);
    }


    @PostMapping()
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }










}
