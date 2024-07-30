package com.example.Banking.customer;


import com.example.Banking.customer.dto.CustomerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequest dto);
}
