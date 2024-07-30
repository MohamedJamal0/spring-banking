package com.example.Banking.repayment;

import com.example.Banking.repayment.dto.RepaymentDto;

import java.math.BigDecimal;
import java.util.List;

public interface RepaymentService {

    RepaymentDto makeRepayment(Long repaymentId);
    void generateRepaymentSchedule(Long loanId);
    List<RepaymentDto> getRepaymentsByLoanId(Long loanId);

}

