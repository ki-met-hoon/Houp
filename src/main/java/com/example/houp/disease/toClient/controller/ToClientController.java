package com.example.houp.disease.toClient.controller;

import com.example.houp.disease.toAI.dto.PredictionResponse;
import com.example.houp.disease.toAI.dto.UserInfoRequest;
import com.example.houp.disease.toClient.service.ToClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/disease-predictions")
@RestController
public class ToClientController {
    private final ToClientService toClientService;

    @PostMapping("")
    PredictionResponse hadleUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        return toClientService.getPredictedDiseaseInfo(userInfoRequest);
    }
}
