package com.example.Banking.billPayment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillPaymentRequest(
        @NotNull(message = "Biller ID is required")
        Long billerId,

        @NotNull(message = "Account ID is required")
        Long accountId,

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @Future(message = "Schedule date must be in the future")
        @NotNull(message = "Schedule date is required")
        LocalDateTime scheduleDate,

        @NotNull(message = "Status is required")
        String status
) {
}
