package com.example.Banking.repayment.dto;

import com.example.Banking.repayment.RepaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RepaymentDto(
        Long id,
        BigDecimal amount,
        LocalDate dueDate,
        RepaymentStatus status
) {
}
