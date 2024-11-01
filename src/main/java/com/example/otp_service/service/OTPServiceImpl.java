package com.example.otp_service.service;

import com.example.otp_service.dto.RegisterCheckDTO;
import com.example.otp_service.dto.ResponseCheckDTO;
import com.example.otp_service.entity.TempOTP;
import com.example.otp_service.repositori.TempOTPRepositori;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OTPServiceImpl implements OTPService{

    private final TempOTPRepositori tempOTPRepositori;

    @Override
    public ResponseCheckDTO requestOTP(RegisterCheckDTO registerCheckDTO) {
        TempOTP tempOTPByEmail = tempOTPRepositori.getFirstByEmail(registerCheckDTO.getEmail());


        System.out.println(tempOTPByEmail);
        // Todo -> jika otp di redis ada , hapus otp nya
        if (tempOTPByEmail != null){
            tempOTPRepositori.delete(tempOTPByEmail);

            return null;

        }

        String randomOTP = generateOTP();

        TempOTP tempOTP = TempOTP.builder()
                .email(registerCheckDTO.getEmail())
                .otp(randomOTP)
                .build();
       TempOTP save = tempOTPRepositori.save(tempOTP);

       return ResponseCheckDTO.builder()
               .id(save.getId())
               .email(save.getEmail())
               .otp(save.getOtp())
               .build();
    }

    private String generateOTP(){
        return new DecimalFormat("0000").format(new Random().nextInt(9999));
    }
}
