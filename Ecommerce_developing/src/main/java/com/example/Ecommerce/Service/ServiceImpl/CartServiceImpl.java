package com.example.Ecommerce.Service.ServiceImpl;

import com.example.Ecommerce.DTO.CartDto;
import com.example.Ecommerce.Entity.Cart;
import com.example.Ecommerce.Entity.CartItem;
import com.example.Ecommerce.Entity.Customer;
import com.example.Ecommerce.Entity.Product;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import com.example.Ecommerce.config.JwtService;
import static com.example.Ecommerce.Mapper.CartMapper.MaptoCartItem;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

public class CartServiceImpl {
    private JwtService jwtService;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;


    public CartServiceImpl(JwtService jwtService, CustomerRepository customerRepository, CartItem cartItem) {
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
    }

    List<CartItem> AddCartItem(CartDto cartDto, HttpServletRequest httpRequest){
        String jwt = httpRequest.getHeader("Authorization").substring(7);
        String email = jwtService.extractUsername(jwt);
        Optional<Customer> user = customerRepository.findByEmail(email);
        Product product = productRepository.findById(cartDto.getProductId());
        CartItem cartItem = MaptoCartItem(product,cartDto.getQuantity());
        Optional<Cart> cart


    }

}
