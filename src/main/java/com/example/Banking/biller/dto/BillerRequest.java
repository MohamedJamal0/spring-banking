package com.example.Banking.biller.dto;


import jakarta.validation.constraints.NotNull;

public record BillerRequest(
        @NotNull(message = "Name is required")
        String name,

        @NotNull(message = "Account number is required")
        String accountNumber
) {
}
