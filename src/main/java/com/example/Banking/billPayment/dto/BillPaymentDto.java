package com.example.Banking.billPayment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BillPaymentDto(
        Long id,
        Long billerId,
        Long accountId,
        BigDecimal amount,
        LocalDateTime scheduleDate,
        String status
) {
}
