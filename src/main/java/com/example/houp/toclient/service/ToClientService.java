package com.example.houp.toclient.service;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toai.service.ToAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToClientService {
    private final ToAIService toAIService;

    public PredictionResponse getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return toAIService.getPredictedDiseaseInfo(userInfoRequest);
    }

    public JudgementDocumentResponse getPredictedDisagnosisReport(CaseExamples caseExamples) {
        return toAIService.getJudgementDocument(caseExamples);
    }
}
