package com.example.houp.support.properties.toai;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "to-ai")
public record ToAiProperties(
        String baseUrl,
        Disease disease,
        Report report
) {
}
