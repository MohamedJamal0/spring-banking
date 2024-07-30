package com.example.Banking.repayment;


import com.example.Banking.repayment.dto.RepaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepaymentMapper {

    RepaymentDto toDto(Repayment repayment);
}
