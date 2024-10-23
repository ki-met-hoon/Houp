package com.example.houp.support.config;

import com.example.houp.support.properties.toai.ToAiProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final ToAiProperties toAiProperties;

    private HttpClient createHttpClient() {
        return HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)
                .responseTimeout(Duration.ofMillis(20000))
                .doOnConnected(connection ->
                        connection
                                .addHandlerLast(
                                        new ReadTimeoutHandler(20000,
                                                TimeUnit.MILLISECONDS))
                                .addHandlerLast(
                                        new WriteTimeoutHandler(500,
                                                TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient diseaseWebClient() {
        return WebClient.builder()
                .baseUrl(toAiProperties.baseUrl())
                .clientConnector(new ReactorClientHttpConnector(createHttpClient()))
                .build();
    }
}
