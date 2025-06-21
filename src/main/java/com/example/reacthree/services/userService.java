package com.example.reacthree.services;


import com.example.reacthree.entity.User;
import com.example.reacthree.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {



    @Autowired
    private userRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public User addUser(User user){

        user.setName(user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }



    public User getUserByName(String UserName)
    {

        return userRepository.findByName(UserName);

    }


    public List<User> getAllusers() {

        return userRepository.findAll();


    }
}
