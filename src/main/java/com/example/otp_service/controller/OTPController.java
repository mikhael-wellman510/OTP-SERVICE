package com.example.otp_service.controller;

import com.example.otp_service.dto.RegisterCheckDTO;
import com.example.otp_service.dto.ResponseCheckDTO;
import com.example.otp_service.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OTPController {

    private final OTPService otpService;

    @PostMapping("/request")
    public ResponseEntity<?> requestOTP(@RequestBody RegisterCheckDTO registerCheckDTO){
      ResponseCheckDTO responseCheckDTO = otpService.requestOTP(registerCheckDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseCheckDTO);
    }

}
