package com.example.Banking.billPayment;

import com.example.Banking.billPayment.dto.BillPaymentDto;
import com.example.Banking.billPayment.dto.BillPaymentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillPaymentMapper {

    BillPayment toEntity(BillPaymentRequest billPaymentRequest);

    BillPaymentDto toDto(BillPayment billPayment);
}
