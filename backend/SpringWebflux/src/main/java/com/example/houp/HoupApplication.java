package com.example.houp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.houp.support.properties")
public class HoupApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoupApplication.class, args);
    }

}
