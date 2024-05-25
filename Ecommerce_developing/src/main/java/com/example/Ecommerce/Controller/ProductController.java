package com.example.Ecommerce.Controller;

import com.example.Ecommerce.DTO.ProductDTO;
import com.example.Ecommerce.Entity.Product;
import com.example.Ecommerce.Handler.ResponseHandler;
import com.example.Ecommerce.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private ProductService productService;
@Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("sellers/add/products")
    private ResponseEntity<Object> AddProduct(@Valid @RequestBody Product product, HttpServletRequest request){
        List<ProductDTO> addedProduct=productService.addProduct(product,request);
       return ResponseHandler.GenerateResponse(addedProduct ,"product is successfully added",HttpStatus.CREATED);
    }}
