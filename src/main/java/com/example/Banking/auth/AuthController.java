package com.example.Banking.auth;


import com.example.Banking.auth.dto.LoginRequest;
import com.example.Banking.customer.dto.CustomerRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/customer")
    ResponseEntity<String> registerCustomer (@Valid @RequestBody CustomerRequest customerRequest) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(authService.registerCustomer(customerRequest));
    }

    @PostMapping("/login")
     ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .ok(authService.login(loginRequest.email() , loginRequest.password()));
    }



}
