package com.example.eco_market.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "product_id")
    private List<Long> productIds;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;
}
