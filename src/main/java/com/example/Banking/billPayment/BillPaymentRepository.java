package com.example.Banking.billPayment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BillPaymentRepository extends JpaRepository<BillPayment , Long> {
    List<BillPayment> findByAccountIdAndScheduledDateAfter(Long accountId, LocalDateTime date);
    List<BillPayment> findByAccountIdAndStatus(Long accountId, String status);
}


