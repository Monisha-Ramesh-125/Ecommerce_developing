package com.example.Ecommerce.Mapper;

import com.example.Ecommerce.Entity.CartItem;
import com.example.Ecommerce.Entity.Product;

public class CartMapper {

    public static CartItem MaptoCartItem(Product product,int quantity){
        CartItem cartItem = CartItem.builder()
                .product(product)
                .quantity(quantity)
                .build();

        return cartItem;
    }}

