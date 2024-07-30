package com.example.Banking.repayment;

import com.example.Banking.repayment.dto.RepaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/repayments")
@RequiredArgsConstructor
public class RepaymentController {

    private final RepaymentService repaymentService;

    @PostMapping("/{repaymentId}")
    public ResponseEntity<RepaymentDto> makeRepayment(@PathVariable Long repaymentId) {
        return ResponseEntity.ok(
                repaymentService.makeRepayment(repaymentId)
        );
    }

    @PostMapping("/schedule/{loanId}")
    public ResponseEntity<Void> generateRepaymentSchedule(@PathVariable Long loanId) {
        repaymentService.generateRepaymentSchedule(loanId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<RepaymentDto>> getRepaymentsByLoanId(@PathVariable Long loanId) {
        return ResponseEntity.ok()
                .body(repaymentService.getRepaymentsByLoanId(loanId));
    }

}
