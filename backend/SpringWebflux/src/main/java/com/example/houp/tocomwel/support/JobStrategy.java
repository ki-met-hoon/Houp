package com.example.houp.tocomwel.support;

import com.example.houp.support.properties.tocomwel.ToComwelProperties;
import com.example.houp.tocomwel.dto.ReportToObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class JobStrategy implements ReportStrategy {

    private final ToComwelProperties toComwelProperties;
    private final String jobKind;

    private JobStrategy(ToComwelProperties toComwelProperties, String jobKind) {
        this.toComwelProperties = toComwelProperties;
        this.jobKind = jobKind;
    }

    public static JobStrategy of(ToComwelProperties toComwelProperties, String jobKind) {
        return new JobStrategy(toComwelProperties, jobKind);
    }

    @Override
    public Mono<ReportToObject> getReports(WebClient toComwelWebClient, String pageNo, String numOfRows) {
        return toComwelWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(toComwelProperties.reportApi())
                        .queryParam("serviceKey", toComwelProperties.serviceKey())
                        .queryParam("kindb", "{jobKind}")
                        .queryParam("pageNo", "{page}")
                        .queryParam("numOfRows", "{rows}")
                        .build(
                                jobKind,
                                pageNo,
                                numOfRows
                        ))
                .retrieve()
                .bodyToMono(ReportToObject.class)
                .doOnError(e -> log.error("[API Error] Failed to fetch report data. cause: {}, message: {}",
                        e.getCause(),
                        e.getMessage()))
                .onErrorResume(e -> Mono.error(new RuntimeException()));
    }
}

