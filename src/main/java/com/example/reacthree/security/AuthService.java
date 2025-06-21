package com.example.reacthree.security;


import com.example.reacthree.entity.User;
import com.example.reacthree.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    private jwtUtils jwtUtils;

    @Autowired
    private userRepository userRepository;


    public User getUsername(String UserName)
    {
        return userRepository.findByName(UserName);
    }


    public String generateToken(String userName)
    {
        return jwtUtils.generateToken(userName);
    }


    public void validateToken(String token)
    {
        jwtUtils.validateToken(token);
    }

}
