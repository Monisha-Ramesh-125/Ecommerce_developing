package com.example.Ecommerce.Entity;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


}


