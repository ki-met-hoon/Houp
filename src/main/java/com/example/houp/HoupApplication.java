package com.example.houp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HoupApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoupApplication.class, args);
    }

}
