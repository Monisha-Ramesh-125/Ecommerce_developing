package com.example.Ecommerce.Service;

import com.example.Ecommerce.DTO.ProductDTO;
import com.example.Ecommerce.Entity.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
  List<ProductDTO> addProduct(Product product, HttpServletRequest request);
}
