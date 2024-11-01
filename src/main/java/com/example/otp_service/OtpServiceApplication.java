package com.example.otp_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OtpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpServiceApplication.class, args);
		System.out.println("Spring Runnin in server 8020");
	}

}


// redis CRUD
//https://medium.com/@mochrks/implementasi-crud-menggunakan-java-springboot-redis-335048bedd1c