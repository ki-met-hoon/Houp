package com.example.houp.disease.toai.support;

import com.example.houp.disease.toai.dto.PredictionResponse;
import com.example.houp.disease.toai.dto.UserInfoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${feign.client.toAI.name}", url = "${feign.client.toAI.base-url}")
public interface ToAIApiCaller {

    @PostMapping("/${feign.client.toAI.disease-url}")
    PredictionResponse getPredictedDiseaseInfo(@RequestBody UserInfoRequest userInfoRequest);
}
