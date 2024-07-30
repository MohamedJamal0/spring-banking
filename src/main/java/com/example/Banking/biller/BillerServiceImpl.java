package com.example.Banking.biller;

import com.example.Banking.biller.dto.BillerDto;
import com.example.Banking.biller.dto.BillerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillerServiceImpl implements BillerService {

    private final BillerRepository billerRepository;
    private final BillerMapper billerMapper;

    @Override
    public BillerDto addBiller(BillerRequest billerRequest) {
        Biller addEntity = billerMapper.toEntity(billerRequest);
        Biller biller = billerRepository.save(addEntity);
        return billerMapper.toDto(biller);
    }
}
