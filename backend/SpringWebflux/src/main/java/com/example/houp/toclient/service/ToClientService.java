package com.example.houp.toclient.service;

import com.example.houp.support.properties.toai.ToAiProperties;
import com.example.houp.support.util.KindValidator;
import com.example.houp.tocomwel.dto.CaseExamples;
import com.example.houp.toclient.support.exception.JobKindExceptionByAi;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.ReportToClient;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.support.exception.*;
import com.example.houp.tocomwel.support.exception.DiseaseKindExceptionByUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ToClientService {

    private final ToAiProperties toAiProperties;
    private final WebClient toAiWebClient;

    public Mono<PredictionResponse> getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return toAiWebClient
                .post()
                .uri(toAiProperties.disease().url())
                .bodyValue(userInfoRequest)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode().equals(HttpStatus.BAD_REQUEST))
                        return Mono.error(new DiseaseRequestException());
                    else
                        return Mono.error(new DiseaseResponseBySeverException());
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new DiseaseResponseByAiException()))
                .bodyToMono(PredictionResponse.class)
                .doOnNext(predictionResponse -> validate(predictionResponse.jobKind(), predictionResponse.diseaseKind()));
    }

    private void validate(String jobKind, String diseaseKind) {
        validateJobKind(jobKind);
        validateDiseaseKind(diseaseKind);
    }

    private void validateJobKind(String jobKind) {
        if (!KindValidator.isValidJobKind(jobKind)) {
            throw new JobKindExceptionByAi();
        }
    }

    private void validateDiseaseKind(String diseaseKind) {
        if (!KindValidator.isValidDiseaseKind(diseaseKind)) {
            throw new DiseaseKindExceptionByUser();
        }
    }

    public Mono<ReportToClient> getPredictedDisagnosisReport(CaseExamples caseExamples) {
        return getJudgementDocument(caseExamples)
                .map(judgeDoc -> convertToReportToClient(judgeDoc, caseExamples));
    }

    public Mono<JudgementDocumentResponse> getJudgementDocument(CaseExamples caseExamples) {
        return toAiWebClient
                .post()
                .uri(toAiProperties.report().url())
                .bodyValue(caseExamples)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode().equals(HttpStatus.BAD_REQUEST))
                        return Mono.error(new JudgeDocRequestException());
                    else
                        return Mono.error(new JudgeDocResponseBySeverException());
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new JudgeDocResponseByAiException()))
                .bodyToMono(JudgementDocumentResponse.class);
    }

    private ReportToClient convertToReportToClient(JudgementDocumentResponse response, CaseExamples caseExamples) {
        return new ReportToClient(
                response.approvalProbability(),
                response.result(),
                new ReportToClient.CaseExamples(
                        convertCase(response.caseExamples().firstCase(), caseExamples),
                        convertCase(response.caseExamples().secondCase(), caseExamples)
                )
        );
    }

    private ReportToClient.Case convertCase(JudgementDocumentResponse.Case similarCase, CaseExamples caseExamples) {
        return new ReportToClient.Case(
                caseExamples.caseExamples().get(similarCase.id()).reviewResult(),
                similarCase.summary(),
                caseExamples.caseExamples().get(similarCase.id()).judgmentDocument()
        );
    }
}
