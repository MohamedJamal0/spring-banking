package com.example.Banking.loan;

import com.example.Banking.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id", nullable = false )
    private Long id;

    @Column(name = "loan_type", nullable = false )
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Column(name = "principal_amount", nullable = false )
    private BigDecimal principalAmount;

    @Column(name = "interest_rate", nullable = false )
    private BigDecimal interestRate;

    @Column(name = "start_Date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_data", nullable = false)
    private LocalDate endDate;

    @Column(name = "status", nullable = false)
    private LoanStatus status;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


}


