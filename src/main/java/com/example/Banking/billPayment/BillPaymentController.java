package com.example.Banking.billPayment;

import com.example.Banking.billPayment.dto.BillPaymentDto;
import com.example.Banking.billPayment.dto.BillPaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill-payments")
@RequiredArgsConstructor
public class BillPaymentController {

    private final BillPaymentService billPaymentService;

    @PostMapping("/schedule")
    public ResponseEntity<BillPaymentDto> scheduleBillPayment(@Valid @RequestBody BillPaymentRequest billPaymentRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(billPaymentService.scheduleBillPayment(billPaymentRequest));
    }

    @GetMapping("/upcoming/{accountId}")
    public ResponseEntity<List<BillPaymentDto>> getUpcomingPayments(@PathVariable Long accountId) {
        return ResponseEntity.ok(billPaymentService.getUpcomingPayments(accountId));
    }

    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<BillPaymentDto>> getPaymentHistory(@PathVariable Long accountId) {
        return ResponseEntity.ok(billPaymentService.getPaymentHistory(accountId));
    }

}
