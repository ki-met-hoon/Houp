package com.example.houp.disease.toai.service;

import com.example.houp.disease.toai.support.ToAIApiCaller;
import com.example.houp.disease.toai.dto.PredictionResponse;
import com.example.houp.disease.toai.dto.UserInfoRequest;
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
