package com.example.houp.disease.toai.support;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "feign.client.toAI")
public record ToAIProperties(
        String name,
        String baseUrl,
        String diseaseUrl
) {
}
