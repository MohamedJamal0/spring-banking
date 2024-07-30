package com.example.Banking.auth.dto;


import jakarta.validation.constraints.NotNull;

public record LoginRequest(

        @NotNull(message = "email is required")
        String email,

        @NotNull(message = "password is required")
        String password

) {
}
