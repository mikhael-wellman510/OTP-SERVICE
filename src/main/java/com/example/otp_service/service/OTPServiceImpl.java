package com.example.otp_service.service;

import com.example.otp_service.dto.EmailDto;
import com.example.otp_service.dto.RegisterCheckDTO;
import com.example.otp_service.dto.ResponseCheckDTO;
import com.example.otp_service.entity.TempOTP;
import com.example.otp_service.repositori.TempOTPRepositori;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class OTPServiceImpl implements OTPService{

    private final TempOTPRepositori tempOTPRepositori;
    private final RedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    @Override
    public ResponseCheckDTO requestOTP(RegisterCheckDTO registerCheckDTO) {
        TempOTP tempOTPByEmail = tempOTPRepositori.getFirstByEmail(registerCheckDTO.getEmail());


        System.out.println(tempOTPByEmail);
        // Todo -> jika otp di redis ada , hapus otp nya
        if (tempOTPByEmail != null){
            tempOTPRepositori.delete(tempOTPByEmail);

            return null;

        }

        // Todo , generate random number
        String randomOTP = generateOTP();

        // Todo -> Save to redis
        TempOTP tempOTP = TempOTP.builder()
                .email(registerCheckDTO.getEmail())
                .otp(randomOTP)
                .build();
       TempOTP save = tempOTPRepositori.save(tempOTP);

       sendEmail(registerCheckDTO.getEmail() ,"Kode Verifikasi anda" + randomOTP);
        System.out.println("Sukses panggil service OTP " + save);
       return ResponseCheckDTO.builder()
               .id(save.getId())
               .email(save.getEmail())
               .otp(save.getOtp())
               .build();
    }

    private void sendEmail(String to,String body){
        log.debug("to : {} , body :{} " , to , body);
        EmailDto emailDto = EmailDto.builder()
                .to(to)
                .subject("Kode Verifikasi :")
                .body(body)
                .build();

        redisTemplate.convertAndSend(channelTopic.getTopic() , emailDto);
    }

    private String generateOTP(){
        return new DecimalFormat("0000").format(new Random().nextInt(9999));
    }
}
