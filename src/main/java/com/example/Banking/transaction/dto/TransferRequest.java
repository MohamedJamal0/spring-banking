package com.example.Banking.transaction.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequest(

        @NotNull(message = "From account ID is required")
        Long fromAccountId,

        @NotNull(message = "To account ID is required")
        Long toAccountId,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount

) {
}
