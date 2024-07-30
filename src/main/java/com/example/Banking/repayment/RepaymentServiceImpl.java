package com.example.Banking.repayment;

import com.example.Banking.account.Account;
import com.example.Banking.account.AccountRepository;
import com.example.Banking.loan.Loan;
import com.example.Banking.loan.LoanRepository;
import com.example.Banking.repayment.dto.RepaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RepaymentServiceImpl implements RepaymentService {

    private final LoanRepository loanRepository;
    private final RepaymentRepository repaymentRepository;
    private final AccountRepository accountRepository;
    private final RepaymentMapper repaymentMapper;

    @Override
    public RepaymentDto makeRepayment(Long repaymentId) {

        Repayment repayment = repaymentRepository.findById(repaymentId).orElseThrow();
        Loan loan = repayment.getLoan();
        Account account = loan.getAccount();

        if ( isBalanceNotSufficient(account , repayment)) {
            throw new RuntimeException("Repayment amount is less than required");
        }

        processRepayment(repayment , account);

        return  repaymentMapper.toDto(repayment);
    }

    private boolean isBalanceNotSufficient(Account account, Repayment repayment) {
        return account.getBalance().compareTo(repayment.getAmount()) < 0;
    }

    private void processRepayment(Repayment repayment, Account account) {
        account.setBalance(account.getBalance().subtract(repayment.getAmount()));
        accountRepository.save(account);

        repayment.setStatus(RepaymentStatus.PAID);
        repaymentRepository.save(repayment);
    }

    @Override
    public void generateRepaymentSchedule(Long loanId) {

        Loan loan = loanRepository.findById(loanId).orElseThrow();

        BigDecimal monthlyRepaymentAmount = calculateMonthlyRepayment(
                loan.getPrincipalAmount(),
                loan.getInterestRate(),
                loan.getEndDate().getMonthValue() - loan.getStartDate().getMonthValue()
        );

        LocalDate repaymentDate = loan.getStartDate().plusMonths(1);

        // create a repayment for every month
        while (repaymentDate.isBefore(loan.getEndDate()) || repaymentDate.isEqual(loan.getEndDate())) {
            Repayment repayment = Repayment.builder()
                    .loan(loan)
                    .amount(monthlyRepaymentAmount)
                    .dueDate(repaymentDate)
                    .status(RepaymentStatus.PENDING)
                    .build();

            repaymentDate = repaymentDate.plusMonths(1);
        }

    }

    private BigDecimal calculateMonthlyRepayment(BigDecimal principal, BigDecimal annualInterestRate, int months) {
        // Calculate monthly interest rate
        BigDecimal monthlyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);

        // Calculate the factor (1 + r)^n
        BigDecimal onePlusMonthlyRate = BigDecimal.ONE.add(monthlyInterestRate);
        BigDecimal factor = onePlusMonthlyRate.pow(months);

        // Calculate the monthly repayment amount
        BigDecimal numerator = monthlyInterestRate.multiply(principal).multiply(factor);
        BigDecimal denominator = factor.subtract(BigDecimal.ONE);
        BigDecimal monthlyRepayment = numerator.divide(denominator, RoundingMode.HALF_UP);

        return monthlyRepayment;
    }

    @Override
    public List<RepaymentDto> getRepaymentsByLoanId(Long loanId) {
        return repaymentRepository
                .findByLoanId(loanId)
                .stream()
                .map(repaymentMapper::toDto)
                .toList();
    }
}



