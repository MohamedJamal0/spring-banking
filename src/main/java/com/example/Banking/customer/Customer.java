package com.example.Banking.customer;


import com.example.Banking.auth.Role;
import com.example.Banking.auth.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@Builder
public class Customer extends User {
    
    @Column(name = "first_name" ,nullable = false)
    private String firstName;

    @Column(name = "last_name" ,nullable = false)
    private String lastName;

    @Column(name = "phone_number" , nullable = false)
    private String phoneNumber;

    @Column(name = "address" , nullable = false)
    private String address;

    public Customer() {
        setRole(Role.CUSTOMER);
    }

}
