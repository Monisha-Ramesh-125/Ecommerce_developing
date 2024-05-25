package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
}
