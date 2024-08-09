package com.example.houp.toclient.controller;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.service.ToClientService;
import com.example.houp.tocomwel.service.ToComwelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ToClientController {
    private final ToClientService toClientService;
    private final ToComwelService toComwelService;

    @PostMapping("/disease-predictions")
    PredictionResponse handleUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        return toClientService.getPredictedDiseaseInfo(userInfoRequest);
    }

    @GetMapping("/worker-compensation-infos")
    JudgementDocumentResponse handleDiseaseInfo(@RequestParam("diseaseName") String diseaseName, @RequestParam("jobKind") String jobKind, @RequestParam("diseaseKind") String diseaseKind) {
        CaseExamples relatedDiseaseJudgments = toComwelService.getDiseaseDisagnosisReport(diseaseName, jobKind, diseaseKind);
        return toClientService.getPredictedDisagnosisReport(relatedDiseaseJudgments);
    }
}
