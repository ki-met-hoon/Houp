package com.example.houp.disease.toAI;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "feign.client.toAI")
public record toAIProperties(
        String name,
        String baseUrl,
        String diseaseUrl
) {
}
