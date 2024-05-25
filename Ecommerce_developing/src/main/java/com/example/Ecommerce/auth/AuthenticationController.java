package com.example.Ecommerce.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> registerCustomer(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerCustomer(request));
    }

    @PostMapping("/register/seller")
    public ResponseEntity<AuthenticationResponse> registerSeller(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerSeller(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
