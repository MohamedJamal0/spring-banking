package com.example.Banking.customer;

import com.example.Banking.auth.Role;
import com.example.Banking.customer.dto.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;


    @Override
    public Customer addCustomer(CustomerRequest customerRequest) {

        Customer addEntity = customerMapper.toEntity(customerRequest);

        addEntity.setPassword(passwordEncoder.encode(customerRequest.password()));
        addEntity.setEmail(customerRequest.email());
        addEntity.setRole(Role.CUSTOMER);


        return customerRepository.save(addEntity);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }
}