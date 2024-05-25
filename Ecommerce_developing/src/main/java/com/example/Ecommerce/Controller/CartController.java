package com.example.Ecommerce.Controller;

import com.example.Ecommerce.DTO.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {

    @GetMapping("/customer/cart/add")
    private ResponseEntity<Object> addProductToCart(CartDto cartDto){

    }


}
