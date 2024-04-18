package com.example.eco_market.Controllers;

import com.example.eco_market.Models.Product;
import com.example.eco_market.Models.User;
import com.example.eco_market.Services.Impls.ProductServiceImpl;
import com.example.eco_market.Services.Impls.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.allUsers();
    }

    @PostMapping("/product/create")
    public String createProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
