package com.example.houp.disease.toClient.service;

import com.example.houp.disease.toAI.dto.PredictionResponse;
import com.example.houp.disease.toAI.dto.UserInfoRequest;
import com.example.houp.disease.toAI.service.ToAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToClientService {
    private final ToAIService toAIService;

    public PredictionResponse getPredictedDiseaseInfo(UserInfoRequest userInfoRequest) {
        return toAIService.getPredictedDiseaseInfo(userInfoRequest);
    }
}
