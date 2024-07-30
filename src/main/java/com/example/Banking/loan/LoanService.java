package com.example.Banking.loan;

import com.example.Banking.loan.dto.LoanDto;
import com.example.Banking.loan.dto.LoanRequest;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    LoanDto applyForLoan(LoanRequest loanRequest);
    LoanDto approveLoan(Long loanId);
    LoanDto getLoanById(Long loanId);
    List<LoanDto> getLoansByAccountId(Long accountId);
}

