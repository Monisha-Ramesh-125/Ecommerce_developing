package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Integer> {
    Optional<Seller> findByEmail(String seller);
}
