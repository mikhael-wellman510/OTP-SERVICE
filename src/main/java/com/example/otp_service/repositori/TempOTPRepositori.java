package com.example.otp_service.repositori;

import com.example.otp_service.entity.TempOTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// Kalau pakai redis , Harus Pakai CRUD , Ga bisa pakai JPARepository
@Repository
public interface TempOTPRepositori extends CrudRepository<TempOTP, String> {

    TempOTP getFirstByEmail(String email);

}
