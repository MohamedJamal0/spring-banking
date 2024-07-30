package com.example.Banking.billPayment;

import com.example.Banking.billPayment.dto.BillPaymentDto;
import com.example.Banking.billPayment.dto.BillPaymentRequest;

import java.util.List;

public interface BillPaymentService {

    BillPaymentDto scheduleBillPayment(BillPaymentRequest billPaymentRequest);
    List<BillPaymentDto> getUpcomingPayments(Long accountId);
    List<BillPaymentDto> getPaymentHistory(Long accountId);
    void executeScheduledPayments();
}

