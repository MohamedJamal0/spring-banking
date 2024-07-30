package com.example.Banking.transaction;

import com.example.Banking.transaction.dto.DepositRequest;
import com.example.Banking.transaction.dto.TransactionDto;
import com.example.Banking.transaction.dto.TransferRequest;
import com.example.Banking.transaction.dto.WithdrawRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/withdraw")
    public ResponseEntity<TransactionDto> withdraw(@Valid @RequestBody WithdrawRequest withdrawRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionService.withdraw(withdrawRequest));
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionDto> deposit(@Valid @RequestBody DepositRequest depositRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionService.deposit(depositRequest));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionDto> transfer(@Valid @RequestBody TransferRequest transferRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionService.transfer(transferRequest));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByAccountId(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccountId(accountId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

}
