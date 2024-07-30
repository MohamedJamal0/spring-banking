package com.example.Banking.transaction;

import com.example.Banking.transaction.dto.DepositRequest;
import com.example.Banking.transaction.dto.TransactionDto;
import com.example.Banking.transaction.dto.TransferRequest;
import com.example.Banking.transaction.dto.WithdrawRequest;

import java.util.List;

public interface TransactionService {

    TransactionDto withdraw(WithdrawRequest withdrawRequest);
    TransactionDto deposit(DepositRequest depositRequest);
    TransactionDto transfer(TransferRequest transferRequest);
    List<TransactionDto> getTransactionsByAccountId(Long accountId);
    TransactionDto getTransactionById(Long id);




}

