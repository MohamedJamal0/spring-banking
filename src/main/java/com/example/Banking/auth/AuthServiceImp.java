package com.example.Banking.auth;

import com.example.Banking.config.JwtService;
import com.example.Banking.customer.Customer;
import com.example.Banking.customer.CustomerService;
import com.example.Banking.customer.dto.CustomerRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final JwtService jwtService;

    @Override
    public String registerCustomer(CustomerRequest customerRequest) {
        Customer customer = customerService.addCustomer(customerRequest);
        return  jwtService.generateToken(customer);
    }


    @Override
    public String login(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        User user = userRepository.findByEmail(email).orElseThrow();
        return jwtService.generateToken(user);
    }


}
