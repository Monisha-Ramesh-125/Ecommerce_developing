package com.example.Ecommerce.config;

import com.example.Ecommerce.Repository.CustomerRepository;
import com.example.Ecommerce.Repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {


    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    @Bean

    public UserDetailsService userDetailsService() {
        return username -> {
            var customer = customerRepository.findByEmail(username);
            if (customer.isPresent()) {
                return customer.get();
            }

            var seller = sellerRepository.findByEmail(username);
            if (seller.isPresent()) {
                return seller.get();
            }

            throw new UsernameNotFoundException("User not found with email: " + username);
        };
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return username -> repository.findByEmail(username)
//                .orElseThrow(()->new UsernameNotFoundException("user not found"));
//    }
//

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

