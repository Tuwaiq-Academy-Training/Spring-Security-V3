package com.example.test123.repository;

import com.example.test123.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByUsername(String username);
}
