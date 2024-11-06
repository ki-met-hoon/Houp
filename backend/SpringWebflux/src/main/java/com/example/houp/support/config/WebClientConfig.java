package com.example.houp.support.config;

import com.example.houp.support.properties.toai.ToAiProperties;
import com.example.houp.support.properties.tocomwel.ToComwelProperties;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final ToAiProperties toAiProperties;
    private final ToComwelProperties toComwelProperties;

    @Bean
    public DefaultUriBuilderFactory reportUriBuilderFactory() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(toComwelProperties.baseUrl());
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        return factory;
    }

    @Bean
    public ExchangeStrategies reportExchangeStrategies() {
        return ExchangeStrategies.builder()
                .codecs(clientCodecConfigurer ->
                        clientCodecConfigurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder()))
                .build();
    }

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
    public WebClient toAiWebClient() {
        return WebClient.builder()
                .baseUrl(toAiProperties.baseUrl())
                .clientConnector(new ReactorClientHttpConnector(createHttpClient()))
                .build();
    }

    @Bean
    public WebClient toComwelWebClient() {
        return WebClient.builder()
                .baseUrl(toComwelProperties.baseUrl())
                .uriBuilderFactory(reportUriBuilderFactory())
                .exchangeStrategies(reportExchangeStrategies())
                .clientConnector(new ReactorClientHttpConnector(createHttpClient()))
                .build();
    }
}
