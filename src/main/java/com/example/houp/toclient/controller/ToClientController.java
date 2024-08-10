package com.example.houp.toclient.controller;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.service.ToClientService;
import com.example.houp.tocomwel.service.ToComwelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ToClientController {
    private final ToClientService toClientService;
    private final ToComwelService toComwelService;

    @PostMapping("/disease-predictions")
    public ResponseEntity<PredictionResponse> handleUserInfo(@Valid @RequestBody UserInfoRequest userInfoRequest) {
        return ResponseEntity.ok(toClientService.getPredictedDiseaseInfo(userInfoRequest));
    }

    @GetMapping("/worker-compensation-infos")
    public ResponseEntity<JudgementDocumentResponse> handleDiseaseInfo(@RequestParam("diseaseName") String diseaseName, @RequestParam("jobKind") String jobKind, @RequestParam("diseaseKind") String diseaseKind) {
        CaseExamples relatedDiseaseJudgments = toComwelService.getDiseaseDisagnosisReport(diseaseName, jobKind, diseaseKind);
        return ResponseEntity.ok(toClientService.getPredictedDisagnosisReport(relatedDiseaseJudgments));
    }
}
