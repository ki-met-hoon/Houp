package com.example.houp.tocomwel.support;

import com.example.houp.support.properties.tocomwel.ToComwelProperties;
import com.example.houp.tocomwel.dto.ReportToObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class TypeStrategy implements ReportStrategy {

    private final ToComwelProperties toComwelProperties;
    private final String diseaseKind;

    private TypeStrategy(ToComwelProperties toComwelProperties, String diseaseKind) {
        this.toComwelProperties = toComwelProperties;
        this.diseaseKind = diseaseKind;
    }

    public static TypeStrategy of(ToComwelProperties toComwelProperties, String diseaseKind) {
        return new TypeStrategy(toComwelProperties, diseaseKind);
    }

    @Override
    public Mono<ReportToObject> getReports(WebClient toComwelWebClient, String pageNo, String numOfRows) {
        return toComwelWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(toComwelProperties.reportApi())
                        .queryParam("serviceKey", toComwelProperties.serviceKey())
                        .queryParam("kindc", "{disease}")
                        .queryParam("pageNo", "{page}")
                        .queryParam("numOfRows", "{rows}")
                        .build(
                                diseaseKind,
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