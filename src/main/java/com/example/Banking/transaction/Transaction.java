package com.example.Banking.transaction;

import com.example.Banking.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name="transactions" , schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="transaction_id" , nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id" , nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "target_account_id" , nullable = false)
    private Account targetAccountId;


    @Column(name="amount")
    private BigDecimal amount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "transaction_time" )
    private Instant transactionTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name="transaction_type" , nullable = false)
    private TransactionType transactionType;


}


