package com.example.Banking.billPayment;

import com.example.Banking.account.Account;
import com.example.Banking.account.AccountRepository;
import com.example.Banking.billPayment.dto.BillPaymentDto;
import com.example.Banking.billPayment.dto.BillPaymentRequest;
import com.example.Banking.biller.Biller;
import com.example.Banking.biller.BillerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillPaymentServiceImpl implements BillPaymentService {

    private final BillPaymentRepository billPaymentRepository;
    private final AccountRepository accountRepository;
    private final BillerRepository billerRepository;
    private final BillPaymentMapper billPaymentMapper;


    @Override
    public BillPaymentDto scheduleBillPayment(BillPaymentRequest billPaymentRequest) {
        Biller biller = billerRepository.findById(billPaymentRequest.billerId()).orElseThrow();
        Account account = accountRepository.findById(billPaymentRequest.accountId()).orElseThrow();

        BillPayment addEntity = billPaymentMapper.toEntity(billPaymentRequest);
        addEntity.setAccount(account);
        addEntity.setBiller(biller);
        BillPayment billPayment =  billPaymentRepository.save(addEntity);

        return billPaymentMapper.toDto(billPayment);
    }

    @Override
    public List<BillPaymentDto> getUpcomingPayments(Long accountId) {
        return billPaymentRepository
                .findByAccountIdAndScheduledDateAfter(accountId, LocalDateTime.now())
                .stream()
                .map((billPayment -> billPaymentMapper.toDto(billPayment)))
                .toList();
    }

    @Override
    public List<BillPaymentDto> getPaymentHistory(Long accountId) {
        return billPaymentRepository
                .findByAccountIdAndStatus(accountId, BillPaymentStatus.PAID.name())
                .stream()
                .map((billPayment -> billPaymentMapper.toDto(billPayment)))
                .toList();
    }

    @Override
    public void executeScheduledPayments() {

        //TODO IMPLEMENT execute schedule payments
    }
}