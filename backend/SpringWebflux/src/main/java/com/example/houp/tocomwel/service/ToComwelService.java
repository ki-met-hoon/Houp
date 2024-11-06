package com.example.houp.tocomwel.service;

import com.example.houp.support.properties.tocomwel.ToComwelProperties;
import com.example.houp.support.util.KindValidator;
import com.example.houp.tocomwel.dto.CaseExamples;
import com.example.houp.toclient.dto.UserDiseaseInfoRequest;
import com.example.houp.tocomwel.dto.ReportToObject;
import com.example.houp.tocomwel.dto.StrategyResult;
import com.example.houp.tocomwel.support.*;
import com.example.houp.tocomwel.support.exception.DiseaseKindExceptionByUser;
import com.example.houp.tocomwel.support.exception.JobKindExceptionByUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class ToComwelService {

    private static final String PAGENO = "1";
    private static final int MAX_REPORTS = 20;

    private final ToComwelProperties toComwelProperties;
    private final WebClient toComwelWebClient;

    public Mono<CaseExamples> getDiseaseDisagnosisReport(UserDiseaseInfoRequest userDiseaseInfoRequest) {
        validate(userDiseaseInfoRequest.jobKind(), userDiseaseInfoRequest.diseaseKind());

        Flux<ReportToObject.Item> reports = collectReportsFromStrategies(getReportStrategies(
                userDiseaseInfoRequest.diseaseName(),
                userDiseaseInfoRequest.jobKind(),
                userDiseaseInfoRequest.diseaseKind()));

        return createCaseExamples(
                userDiseaseInfoRequest.diseaseName(),
                userDiseaseInfoRequest.jobKind(),
                userDiseaseInfoRequest.diseaseKind(),
                userDiseaseInfoRequest.painDescription(),
                reports
        );
    }

    private void validate(String jobKind, String diseaseKind) {
        validateJobKind(jobKind);
        validateDiseaseKind(diseaseKind);
    }

    private void validateJobKind(String jobKind) {
        if (!KindValidator.isValidJobKind(jobKind)) {
            throw new JobKindExceptionByUser();
        }
    }

    private void validateDiseaseKind(String diseaseKind) {
        if (!KindValidator.isValidDiseaseKind(diseaseKind)) {
            throw new DiseaseKindExceptionByUser();
        }
    }

    private Flux<ReportStrategy> getReportStrategies(String diseaseName, String jobKind, String diseaseKind) {
        return Flux.just(
                DiseaseJobTypeStrategy.of(toComwelProperties, diseaseName, jobKind, diseaseKind),
                 JobTypeStrategy.of(toComwelProperties, jobKind, diseaseKind),
                 TypeStrategy.of(toComwelProperties, diseaseKind),
                 JobStrategy.of(toComwelProperties, jobKind)
        );
    }

    private Flux<ReportToObject.Item> collectReportsFromStrategies(Flux<ReportStrategy> strategies) {
        AtomicInteger remainingCount = new AtomicInteger(MAX_REPORTS);

        return strategies
                .takeWhile(strategy -> remainingCount.get() > 0)
                .flatMap(strategy ->
                        executeStrategy(strategy, remainingCount.get())
                                .doOnNext(result -> remainingCount.addAndGet(-result.count()))
                                .flatMapMany(result ->
                                        Flux.fromIterable(
                                                Optional.ofNullable(result.items())
                                                        .orElse(Collections.emptyList())
                                        )
                                )
                );
    }

    private Mono<StrategyResult> executeStrategy(ReportStrategy strategy, int numOfRow) {
        return strategy.getReports(toComwelWebClient, PAGENO, String.valueOf(numOfRow))
                .map(reportToObject -> {
                    int totalCount = Integer.parseInt(reportToObject.getBody().getTotalCount());
                    List<ReportToObject.Item> reports = reportToObject.getBody().getItems().getItem();
                    return StrategyResult.of(reports, totalCount);
                });
    }

    private Mono<CaseExamples> createCaseExamples(
            String diseaseName,
            String jobKind,
            String diseaseKind,
            String painDescription,
            Flux<ReportToObject.Item> items) {

        AtomicInteger index = new AtomicInteger(0);

        return items
                .distinct()
                .map(item -> new CaseExamples.CaseExample(
                        index.getAndIncrement(),
                        item.getKinda(),
                        item.getNoncontent()
                ))
                .collectList()
                .map(caseExamples -> new CaseExamples(
                        diseaseName,
                        jobKind,
                        diseaseKind,
                        painDescription,
                        caseExamples
                ));
    }
}
