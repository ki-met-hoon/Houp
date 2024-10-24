package com.example.houp.support.properties.tocomwel;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "to-comwel")
public record ToComwelProperties(
        String name,
        String baseUrl,
        String reportApi,
        String serviceKey
) {
}
