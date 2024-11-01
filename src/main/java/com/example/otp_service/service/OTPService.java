package com.example.otp_service.service;

import com.example.otp_service.dto.RegisterCheckDTO;
import com.example.otp_service.dto.ResponseCheckDTO;

public interface OTPService {

    ResponseCheckDTO requestOTP(RegisterCheckDTO registerCheckDTO);
}
