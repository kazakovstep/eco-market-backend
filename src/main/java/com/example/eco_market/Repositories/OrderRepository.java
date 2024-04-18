package com.example.eco_market.Repositories;

import com.example.eco_market.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
