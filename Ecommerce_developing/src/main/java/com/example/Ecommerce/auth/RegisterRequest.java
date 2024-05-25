package com.example.Ecommerce.auth;

import com.example.Ecommerce.Entity.CreditCard;
import com.example.Ecommerce.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String mobile;
    private String email;
    private String password;
    private Role role;
    private CreditCard creditCard;
}