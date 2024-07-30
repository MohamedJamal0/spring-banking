package com.example.Banking.loan.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;

public record LoanRequest(
        @NotNull(message = "Loan type is Required")
        String loanType,

        @NotNull(message = "Principal amount is Required")
        @Positive(message = "Principal amount must be positive")
        @DecimalMin(value = "0.01", inclusive = true, message = "Principal amount must be at least 0.01")
        BigDecimal principalAmount,

        @NotNull(message = "Interest rate is Required")
        @DecimalMin(value = "0.0", inclusive = true, message = "Interest rate must be at least 0.0")
        @DecimalMax(value = "1.0", inclusive = true, message = "Interest rate must not exceed 1.0")
        BigDecimal interestRate,

        @NotNull(message = "Start date is Required")
        @FutureOrPresent(message = "Start date must be in the present or future")
        Instant startDate,

        @NotNull(message = "End date is Required")
        @FutureOrPresent(message = "End date must be in the present or future")
        Instant endDate,

        @NotNull(message = "Account ID is Required")
        Long accountId
) {}
