package com.example.Banking.biller;

import com.example.Banking.biller.dto.BillerDto;
import com.example.Banking.biller.dto.BillerRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billers")
@RequiredArgsConstructor
public class BillerController {

    private final BillerService billerService;

    @PostMapping
    public ResponseEntity<BillerDto> addBiller(@Valid @RequestBody BillerRequest billerRequest) {
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(billerService.addBiller(billerRequest));
    }


}
