package com.example.houp.toclient.service;

import com.example.houp.support.properties.toai.ToAiProperties;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.support.exception.DiseaseResponseByAiException;
import com.example.houp.toclient.support.exception.DiseaseResponseBySeverException;
import com.example.houp.toclient.support.exception.PredictionResponseParsingException;
import com.example.houp.toclient.support.exception.UserInfoRequestException;
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
    private final WebClient diseaseWebClient;

    public Mono<PredictionResponse> getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return diseaseWebClient
                .post()
                .uri(toAiProperties.disease().url())
                .bodyValue(userInfoRequest)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode().equals(HttpStatus.BAD_REQUEST))
                        return Mono.error(new UserInfoRequestException());
                    else
                        return Mono.error(new DiseaseResponseBySeverException());
                })
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new DiseaseResponseByAiException()))
                .bodyToMono(PredictionResponse.class)
                .onErrorResume(error -> Mono.error(new PredictionResponseParsingException()));
    }

//    public ReportToClient getPredictedDisagnosisReport(CaseExamples caseExamples) {
//        JudgementDocumentResponse judgementDocumentResponse = toAIService.getJudgementDocument(caseExamples);
//        return convertToReportToClient(judgementDocumentResponse, caseExamples);
//    }
//
//    private ReportToClient convertToReportToClient(JudgementDocumentResponse response, CaseExamples caseExamples) {
//        return new ReportToClient(
//                response.approvalProbability(),
//                response.result(),
//                new ReportToClient.CaseExamples(
//                        convertCase(response.caseExamples().firstCase(), caseExamples),
//                        convertCase(response.caseExamples().secondCase(), caseExamples)
//                )
//        );
//    }
//
//    private ReportToClient.Case convertCase(JudgementDocumentResponse.Case similarCase, CaseExamples caseExamples) {
//        return new ReportToClient.Case(
//                caseExamples.caseExamples().get(similarCase.id()).reviewResult(),
//                similarCase.summary(),
//                caseExamples.caseExamples().get(similarCase.id()).judgmentDocument()
//        );
//    }
}
