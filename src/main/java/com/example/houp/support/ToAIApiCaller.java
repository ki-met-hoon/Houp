package com.example.houp.support;

import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.toclient.dto.JudgementDocumentResponse;
import com.example.houp.toclient.dto.PredictionResponse;
import com.example.houp.toclient.dto.UserInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feign.client.toAI.name}", url = "${feign.client.toAI.base-url}")
public interface ToAIApiCaller {

    @PostMapping("${feign.client.toAI.disease-url}")
    PredictionResponse getPredictedDiseaseInfo(@RequestBody UserInfoRequest userInfoRequest);

    @PostMapping("${feign.client.toAI.report-url}")
    JudgementDocumentResponse getPredictedDisagnosisReport(@RequestBody CaseExamples caseExamples);
}
