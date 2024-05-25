package com.example.Ecommerce.token;


import com.example.Ecommerce.Entity.Customer;
import com.example.Ecommerce.Entity.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;


    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    public User user;
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "customer_id") // Adjust the column name to match your database schema
private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id") // Adjust the column name to match your database schema
    private Seller seller;
}