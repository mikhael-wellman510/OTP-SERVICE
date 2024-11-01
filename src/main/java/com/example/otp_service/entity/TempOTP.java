package com.example.otp_service.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@RedisHash(value = "otp" ,timeToLive = 300)
public class TempOTP {
    @Id
    private String id;
    private String otp;
    private String email;
}
