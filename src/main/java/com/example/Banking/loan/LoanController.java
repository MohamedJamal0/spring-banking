package com.example.Banking.loan;

import com.example.Banking.loan.dto.LoanDto;
import com.example.Banking.loan.dto.LoanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<LoanDto> applyForLoan(@RequestBody LoanRequest loanRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loanService.applyForLoan(loanRequest));
    }

    @PostMapping("/{loanId}/approve")
    public ResponseEntity<LoanDto> approveLoan(@PathVariable Long loanId) {
        return ResponseEntity
                .ok()
                .body(loanService.approveLoan(loanId));
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDto> getLoanById(@PathVariable Long loanId) {
        return ResponseEntity
                .ok()
                .body(loanService.getLoanById(loanId));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<LoanDto>> getLoansByAccountId(@PathVariable Long accountId) {
        return ResponseEntity
                .ok()
                .body(loanService.getLoansByAccountId(accountId));
    }
}
