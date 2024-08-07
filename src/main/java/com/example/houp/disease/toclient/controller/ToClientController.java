package com.example.houp.disease.toclient.controller;

import com.example.houp.disease.toai.dto.PredictionResponse;
import com.example.houp.disease.toai.dto.UserInfoRequest;
import com.example.houp.disease.toclient.service.ToClientService;
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
