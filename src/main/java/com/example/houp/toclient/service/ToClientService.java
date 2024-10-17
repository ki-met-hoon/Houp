package com.example.houp.toclient.service;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.ReportToClient;
import com.example.houp.toclient.dto.UserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class ToClientService {

    @Value("${toAI.base-url}")
    private String toAiBaseUrl;

    @Value("${toAI.disease.url}")
    private String toAiDiseaseUrl;

    public Mono<PredictionResponse> getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return WebClient.create(toAiBaseUrl)
                .post()
                .uri(toAiDiseaseUrl)
                .bodyValue(userInfoRequest)
                .retrieve()
                .bodyToMono(PredictionResponse.class);
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
