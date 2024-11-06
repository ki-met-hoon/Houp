package com.example.houp.toclient.handler;

import com.example.houp.support.util.RequestValidator;
import com.example.houp.toclient.dto.UserDiseaseInfoRequest;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.service.ToClientService;
import com.example.houp.tocomwel.service.ToComwelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ToClientHandler {

    private final ToClientService toClientService;
    private final ToComwelService toComwelService;
    private final RequestValidator<UserInfoRequest> userInfoValidator;
    private final RequestValidator<UserDiseaseInfoRequest> userDiseaseInfoValidator;

    public Mono<ServerResponse> handleUserInfo(ServerRequest request) {
        return request.bodyToMono(UserInfoRequest.class)
                .doOnNext(userInfoValidator::validate)
                .flatMap(toClientService::getPredictedDiseaseInfo)
                .flatMap(predictionResponse -> ServerResponse.ok().bodyValue(predictionResponse));
    }

    public Mono<ServerResponse> handleDiseaseInfo(ServerRequest request) {
        return request.bodyToMono(UserDiseaseInfoRequest.class)
                .doOnNext(userDiseaseInfoValidator::validate)
                .flatMap(toComwelService::getDiseaseDisagnosisReport)
                .flatMap(toClientService::getPredictedDisagnosisReport)
                .flatMap(reportToClient -> ServerResponse.ok().bodyValue(reportToClient));
    }
}
