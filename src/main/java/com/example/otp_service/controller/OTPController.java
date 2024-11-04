package com.example.otp_service.controller;

import com.example.otp_service.dto.RegisterCheckDTO;
import com.example.otp_service.dto.ResponseCheckDTO;
import com.example.otp_service.service.OTPService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OTPController {

    private final OTPService otpService;
    private final Environment environment;

    @PostMapping("/request")
    public ResponseEntity<ResponseCheckDTO> requestOTP(@RequestBody RegisterCheckDTO registerCheckDTO){
      ResponseCheckDTO responseCheckDTO = otpService.requestOTP(registerCheckDTO);

        System.out.println("Hasil nya: "  + responseCheckDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseCheckDTO);
    }

    // Ini untuk di consume di account service  . dan juga untuk cek dia memanggil service apa
    @GetMapping("/test-loadbalancer")
    public String testLoadBalancer(){
        String port = environment.getProperty("local.server.port");
        System.out.println("Bjir");
        return "oke with port " + port;
    }

}
