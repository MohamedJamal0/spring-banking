package com.example.Banking.biller;


import com.example.Banking.biller.dto.BillerRequest;
import com.example.Banking.biller.dto.BillerDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BillerMapper {

    Biller toEntity(BillerRequest billerRequest);

    BillerDto toDto(Biller biller);
}
