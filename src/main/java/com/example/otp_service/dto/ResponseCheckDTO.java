package com.example.otp_service.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResponseCheckDTO {
    private String id;
    private String otp;
    private String email;

    @Override
    public String toString() {
        return "ResponseCheckDTO{" +
                "id='" + id + '\'' +
                ", otp='" + otp + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
