package com.example.eco_market.Services;

import com.example.eco_market.Models.User;

import java.util.List;

public interface UserService {
    User getCurrentUser();
    List<User> allUsers();
    void saveUser(User user);
    boolean deleteUser(Long userId);
    List<User> userGetList(Long idMin);
}
