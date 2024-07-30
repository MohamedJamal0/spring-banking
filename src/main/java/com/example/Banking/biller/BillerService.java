package com.example.Banking.biller;

import com.example.Banking.biller.dto.BillerDto;
import com.example.Banking.biller.dto.BillerRequest;


public interface BillerService {
    BillerDto addBiller(BillerRequest billerRequest);
}
