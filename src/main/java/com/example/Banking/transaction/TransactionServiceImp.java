package com.example.Banking.transaction;

import com.example.Banking.account.Account;
import com.example.Banking.account.AccountRepository;
import com.example.Banking.transaction.dto.DepositRequest;
import com.example.Banking.transaction.dto.TransactionDto;
import com.example.Banking.transaction.dto.TransferRequest;
import com.example.Banking.transaction.dto.WithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp  implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;


    @Override
    @Transactional
    public TransactionDto withdraw(WithdrawRequest withdrawRequest) {

        Account account = accountRepository.findById(withdrawRequest.accountId())
                .orElseThrow(() -> new RuntimeException("account is not found"));

        if (account.getBalance().compareTo(withdrawRequest.amount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(withdrawRequest.amount()));

        accountRepository.save(account);

        Transaction addEntity = Transaction.builder()
                .account(account)
                .amount(withdrawRequest.amount())
                .transactionType(TransactionType.WITHDRAWAL)
                .transactionTime(Instant.now())
                .build();

        Transaction transaction = transactionRepository.save(addEntity);

        return transactionMapper.toDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto deposit(DepositRequest depositRequest) {

        Account account = accountRepository.findById(depositRequest.accountId())
                .orElseThrow(() -> new RuntimeException("account not found"));

        account.setBalance(account.getBalance().add(depositRequest.amount()));

        accountRepository.save(account);

        Transaction addEntity = Transaction.builder()
                .account(account)
                .amount(depositRequest.amount())
                .transactionType(TransactionType.DEPOSIT)
                .transactionTime(Instant.now())
                .build();

        Transaction transaction = transactionRepository.save(addEntity);

        return transactionMapper.toDto(transaction);
    }

    @Override
    @Transactional
    public TransactionDto transfer(TransferRequest transferRequest) {

        Account fromAccount = accountRepository.findById(transferRequest.fromAccountId())
                .orElseThrow(() -> new RuntimeException("account not found"));

        Account toAccount = accountRepository.findById(transferRequest.toAccountId())
                .orElseThrow(() -> new RuntimeException("account not found"));

        if (fromAccount.getBalance().compareTo(transferRequest.amount()) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.amount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.amount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction addEntity = Transaction.builder()
                .account(fromAccount)
                .targetAccountId(toAccount)
                .amount(transferRequest.amount())
                .transactionType(TransactionType.TRANSFER)
                .transactionTime(Instant.now())
                .build();

        Transaction transaction = transactionRepository.save(addEntity);

        return transactionMapper.toDto(transaction);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Long accountId) {
        return transactionRepository
                .findByAccountId(accountId)
                .stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        return transactionRepository
                .findById(id)
                .map(transactionMapper::toDto)
                .orElseThrow();
    }

}