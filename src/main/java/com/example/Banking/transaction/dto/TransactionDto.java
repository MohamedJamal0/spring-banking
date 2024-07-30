package com.example.Banking.transaction.dto;

import com.example.Banking.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;

public record TransactionDto(
        Long id,
        Long accountId,
        Long targetAccountId,
        BigDecimal amount,
        Instant transactionTime,
        TransactionType transactionType,
        Instant createdAt,
        Instant updatedAt
) {}
