package com.example.eco_market.Repositories;

import com.example.eco_market.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findByEmail(String email);
}
