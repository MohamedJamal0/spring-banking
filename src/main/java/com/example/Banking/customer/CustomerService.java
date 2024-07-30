package com.example.Banking.customer;


import com.example.Banking.customer.dto.CustomerRequest;

public interface CustomerService {

    Customer addCustomer(CustomerRequest customerAddRequest);
    Customer findById(Long id);
}
