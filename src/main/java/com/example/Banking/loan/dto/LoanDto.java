package com.example.Banking.loan.dto;

import com.example.Banking.loan.LoanStatus;
import com.example.Banking.loan.LoanType;

import java.math.BigDecimal;
import java.time.Instant;

public record LoanDto(
        Long id,
        LoanType loanType,
        BigDecimal principalAmount,
        BigDecimal interestRate,
        Instant startDate,
        Instant endDate,
        LoanStatus status

) {}