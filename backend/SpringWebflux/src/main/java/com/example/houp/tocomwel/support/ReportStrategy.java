package com.example.houp.tocomwel.support;

import com.example.houp.tocomwel.dto.ReportToObject;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public interface ReportStrategy {

    Mono<ReportToObject> getReports(WebClient reportWebClient, String pageNo, String numOfRows);
}
