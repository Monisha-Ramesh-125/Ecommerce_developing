package com.example.Ecommerce.auth;
import com.example.Ecommerce.Entity.Customer;
import com.example.Ecommerce.Entity.Seller;
import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.SellerRepository;
import com.example.Ecommerce.config.JwtService;
import com.example.Ecommerce.token.Token;
import com.example.Ecommerce.token.TokenRepository;
import com.example.Ecommerce.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(request.getRole())
//                .build();
//        var savedUser = repository.save(user);
//        var jwtToken = jwtService.GenerateToken(user);
//        saveUserToken(savedUser, jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }


    public AuthenticationResponse registerCustomer(RegisterRequest request) {
        Optional<Seller> existingSeller = sellerRepository.findByEmail(request.getEmail());
        if (existingSeller.isPresent()) {
            throw new RuntimeException("Email is already registered as a seller.");
        }
        var customer = Customer.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .creditCard(request.getCreditCard())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();



        var savedCustomer = customerRepository.save(customer);

        var jwtToken = jwtService.GenerateToken(customer);
        saveUserToken(savedCustomer, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();


    }

    public AuthenticationResponse registerSeller(RegisterRequest request) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(request.getEmail());
        if (existingCustomer.isPresent()) {
            throw new RuntimeException("Email is already registered as a customer.");
        }


        var seller = Seller.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        var savedSeller = sellerRepository.save(seller);

        var jwtToken = jwtService.GenerateToken(seller);
        saveUserToken(savedSeller, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }





//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = repository.findByEmail(request.getEmail())
//                   .orElseThrow();
//        var jwtToken = jwtService.GenerateToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }
public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    var customer = customerRepository.findByEmail(request.getEmail());
    if (customer.isPresent()) {
        var jwtToken = jwtService.GenerateToken(customer.get());
        revokeAllCustomerTokens(customer.get());
        saveUserToken(customer.get(), jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // Check if the user is a seller
    var seller = sellerRepository.findByEmail(request.getEmail());
    if (seller.isPresent()) {
        var jwtToken = jwtService.GenerateToken(seller.get());
        revokeAllSellerTokens(seller.get());
        saveUserToken(seller.get(), jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // If the user is neither a customer nor a seller, throw an exception
    throw new RuntimeException("User not found.");
}


    private void saveUserToken(Customer savedCustomer, String jwtToken) {
        var token = Token.builder()
                .customer(savedCustomer)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }

    private void saveUserToken(Seller savedSeller, String jwtToken) {
        var token = Token.builder()
                .seller(savedSeller)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .build();
        tokenRepository.save(token);
    }
//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }


    private void revokeAllCustomerTokens(Customer customer) {
        var validCustomerTokens = tokenRepository.findAllValidTokenByCustomerId(customer.getId());
        if (validCustomerTokens.isEmpty())
            return;
        validCustomerTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validCustomerTokens);
    }

    private void revokeAllSellerTokens(Seller seller) {
        var validSellerTokens = tokenRepository.findAllValidTokenBySellerId(seller.getId());
        if (validSellerTokens.isEmpty())
            return;
        validSellerTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validSellerTokens);
    }



}
