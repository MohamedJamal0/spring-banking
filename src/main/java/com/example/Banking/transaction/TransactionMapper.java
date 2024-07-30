package com.example.Banking.transaction;

import com.example.Banking.transaction.dto.TransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(Transaction transaction);
}
