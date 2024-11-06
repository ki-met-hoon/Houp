package com.example.houp.tocomwel.support;

import com.example.houp.support.properties.tocomwel.ToComwelProperties;
import com.example.houp.tocomwel.dto.ReportToObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class DiseaseJobTypeStrategy implements ReportStrategy {

    private final ToComwelProperties toComwelProperties;
    private final String diseaseName;
    private final String jobKind;
    private final String diseaseKind;

    public DiseaseJobTypeStrategy(ToComwelProperties toComwelProperties, String diseaseName, String jobKind, String diseaseKind) {
        this.toComwelProperties = toComwelProperties;
        this.diseaseName = diseaseName;
        this.jobKind = jobKind;
        this.diseaseKind = diseaseKind;
    }

    public static DiseaseJobTypeStrategy of(ToComwelProperties toComwelProperties, String diseaseName, String jobKind, String diseaseKind) {
        return new DiseaseJobTypeStrategy(toComwelProperties, diseaseName, jobKind, diseaseKind);
    }

    @Override
    public Mono<ReportToObject> getReports(WebClient toComwelWebClient, String pageNo, String numOfRows) {
        return toComwelWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(toComwelProperties.reportApi())
                        .queryParam("serviceKey", toComwelProperties.serviceKey())
                        .queryParam("kindb", "{jobKind}")
                        .queryParam("kindc", "{disease}")
                        .queryParam("cq", "{diseaseName}")
                        .queryParam("pageNo", "{page}")
                        .queryParam("numOfRows", "{rows}")
                        .build(
                                jobKind,
                                diseaseKind,
                                diseaseName,
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
