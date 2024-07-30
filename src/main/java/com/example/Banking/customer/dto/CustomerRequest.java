package com.example.Banking.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CustomerRequest(

        @NotNull(message = "First name is required")
        String firstName,

        @NotNull(message = "Last name is required")
        String lastName,

        @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
        String phoneNumber,

        @NotNull
        String address,

        @NotNull(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,

        @NotNull(message = "Password is required")
        String password
) {
}
