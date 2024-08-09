package com.example.houp.support;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL; // 추후에 로깅 레벨 조절 필요. 현재는 개발 단계라서 FULL로 설정
    }
}
