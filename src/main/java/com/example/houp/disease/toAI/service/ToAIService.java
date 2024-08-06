package com.example.houp.disease.toAI.service;

import com.example.houp.disease.toAI.support.ToAIApiCaller;
import com.example.houp.disease.toAI.dto.PredictionResponse;
import com.example.houp.disease.toAI.dto.UserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToAIService {
    private final ToAIApiCaller toAIApiCaller;

    public PredictionResponse getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return toAIApiCaller.getPredictedDiseaseInfo(userInfoRequest);
    }
}
