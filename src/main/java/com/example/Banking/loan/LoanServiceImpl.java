package com.example.Banking.loan;

import com.example.Banking.account.Account;
import com.example.Banking.account.AccountRepository;
import com.example.Banking.loan.dto.LoanDto;
import com.example.Banking.loan.dto.LoanRequest;
import com.example.Banking.repayment.RepaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final AccountRepository accountRepository;
    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    @Override
    public LoanDto applyForLoan(LoanRequest loanRequest) {
        Account account = accountRepository.findById(loanRequest.accountId()).orElseThrow();

        Loan addEntity = loanMapper.toEntity(loanRequest);
        addEntity.setAccount(account);
        addEntity.setStatus(LoanStatus.PENDING);

        Loan loan = loanRepository.save(addEntity);
        return loanMapper.toDto(loan);
    }

    @Override
    public LoanDto approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.APPROVED);
        loanRepository.save(loan);

        return  loanMapper.toDto(loan);
    }

    @Override
    public LoanDto getLoanById(Long loanId) {
        return loanRepository
                .findById(loanId)
                .map(loanMapper::toDto)
                .orElseThrow();
    }

    @Override
    public List<LoanDto> getLoansByAccountId(Long accountId) {
        return loanRepository
                .findByAccountId(accountId)
                .stream()
                .map(loanMapper::toDto)
                .toList();
    }


}
