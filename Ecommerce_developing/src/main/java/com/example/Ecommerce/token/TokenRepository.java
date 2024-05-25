package com.example.Ecommerce.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface TokenRepository extends JpaRepository<Token,Integer> {
    @Query("""
        select t from Token t 
        inner join Customer c on t.customer.Id = c.Id 
        where c.Id = :id and (t.expired = false or t.revoked = false)
        """)
    List<Token> findAllValidTokenByCustomerId(Integer id);

    @Query("""
        select t from Token t 
        inner join Seller s on t.seller.Id = s.Id 
        where s.Id = :id and (t.expired = false or t.revoked = false)
        """)
    List<Token> findAllValidTokenBySellerId(Integer id);


    Optional<Token> findByToken(String token);

    Token save(Token token);
}