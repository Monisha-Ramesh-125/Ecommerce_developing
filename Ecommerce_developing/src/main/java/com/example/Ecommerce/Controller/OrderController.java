package com.example.Ecommerce.Controller;

import com.example.Ecommerce.Entity.Orders;
import com.example.Ecommerce.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/api")


public class OrderController {


    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/customer/add/product")
    public ResponseEntity<Object> addproduct(Orders orders){
   return
    }
}
