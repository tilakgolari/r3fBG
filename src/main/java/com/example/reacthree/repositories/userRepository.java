package com.example.reacthree.repositories;


import com.example.reacthree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Integer>
{


    User findByName(String userName);

}
