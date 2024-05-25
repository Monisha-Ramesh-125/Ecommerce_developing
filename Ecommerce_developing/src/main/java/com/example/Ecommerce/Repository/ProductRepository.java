package com.example.Ecommerce.Repository;

import com.example.Ecommerce.Entity.Product;
import com.example.Ecommerce.Entity.Seller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findBySeller(Seller seller);
    Optional<Product> findByNameAndDescription(String name, String description);
    Product findById(int id);

}
