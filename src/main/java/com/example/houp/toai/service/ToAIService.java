package com.example.houp.toai.service;

import com.example.houp.support.ToAIApiCaller;
import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToAIService {
    private final ToAIApiCaller toAIApiCaller;

    public PredictionResponse getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return toAIApiCaller.getPredictedDiseaseInfo(userInfoRequest);
    }

    public JudgementDocumentResponse getJudgementDocument(CaseExamples caseExamples) {
        return toAIApiCaller.getPredictedDisagnosisReport(caseExamples);
    }
}
