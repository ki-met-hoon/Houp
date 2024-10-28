package com.example.houp.toclient.handler;

import com.example.houp.support.util.RequestValidator;
import com.example.houp.toclient.dto.UserInfoRequest;
import com.example.houp.toclient.service.ToClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ToClientHandler {
    private final ToClientService toClientService;
    private final RequestValidator<UserInfoRequest> validator;

    public Mono<ServerResponse> handleUserInfo(ServerRequest request) {
        return request.bodyToMono(UserInfoRequest.class)
                .doOnNext(validator::validate)
                .flatMap(toClientService::getPredictedDiseaseInfo)
                .flatMap(predictionResponse -> ServerResponse.ok().bodyValue(predictionResponse));
    }
}
