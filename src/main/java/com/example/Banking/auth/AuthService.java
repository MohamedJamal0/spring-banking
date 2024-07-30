package com.example.Banking.auth;


import com.example.Banking.customer.dto.CustomerRequest;

public interface AuthService {
    String registerCustomer(CustomerRequest customerRequest);
    String login(String email , String password);
}
