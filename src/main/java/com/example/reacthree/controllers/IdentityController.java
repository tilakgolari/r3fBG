package com.example.reacthree.controllers;


import com.example.reacthree.dto.AuthRequest;
import com.example.reacthree.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/identity")
public class IdentityController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest)
    {
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword()));

        if(authentication.isAuthenticated())
        {
            System.out.println("Inside token auth");
            return authService.generateToken(authRequest.getUserName());

        }
        else {
            System.out.println("Inside token error");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");

        }



    }
    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token)
    {
        authService.validateToken(token);
        return "Token is Valid";
    }










}
