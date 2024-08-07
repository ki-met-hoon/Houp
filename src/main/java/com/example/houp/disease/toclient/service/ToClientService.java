package com.example.houp.disease.toclient.service;

import com.example.houp.disease.toai.dto.PredictionResponse;
import com.example.houp.disease.toai.dto.UserInfoRequest;
import com.example.houp.disease.toai.service.ToAIService;
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
