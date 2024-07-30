package com.example.Banking.loan;

import com.example.Banking.loan.dto.LoanDto;
import com.example.Banking.loan.dto.LoanRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    Loan toEntity(LoanRequest loanRequest);

    LoanDto toDto(Loan loan);
}
