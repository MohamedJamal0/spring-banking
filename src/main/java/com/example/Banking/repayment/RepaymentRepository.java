package com.example.Banking.repayment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<Repayment , Long> {

    List<Repayment> findByLoanId(Long loanId);
}
