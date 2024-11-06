package com.example.houp.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNotFoundException(NotFoundException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage())));
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBadRequestException(BadRequestException e) {
        return Mono.just(ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleRuntimeException(RuntimeException e) {
        log.debug(e.getMessage());
        return Mono.just(ResponseEntity.internalServerError().body(new ErrorResponse("서버에 알 수 없는 문제가 발생했습니다.")));
    }
}
