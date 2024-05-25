package com.example.Ecommerce.Service;

import com.example.Ecommerce.DTO.CartDto;
import com.example.Ecommerce.Entity.CartItem;

import java.net.http.HttpRequest;
import java.util.List;

public interface CartService {

    List<CartItem> AddCartItem(CartDto cartDto, HttpRequest httpRequest);
}
