package com.example.eco_market.Controllers;

import com.example.eco_market.DTO.RegistrationUserDto;
import com.example.eco_market.Models.User;
import com.example.eco_market.Services.Impls.UserServiceImpl;
import com.example.eco_market.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User userForm) {
        userService.saveUser(userForm);
        return ResponseEntity.ok(new RegistrationUserDto(userForm.getEmail(), userForm.getPassword()));
    }
}
