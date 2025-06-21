package com.example.reacthree.security;

import com.example.reacthree.entity.User;
import com.example.reacthree.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomerUserDetailsService implements UserDetailsService {


    private final userRepository userRepository;

    @Autowired
    public CustomerUserDetailsService(userRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("inside CUD");
        System.out.println(username);
        User user=userRepository.findByName(username);
        System.out.println("Loaded user: " + user.getName() + ", password: " + user.getPassword());


        if (user == null) {

            System.out.println("User not found in DB: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return AuthUser.builder()
                .user(user)
                .build();
    }







}
