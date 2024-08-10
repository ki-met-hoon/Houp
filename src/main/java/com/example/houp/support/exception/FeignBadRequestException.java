package com.example.houp.support.exception;

import com.example.houp.advice.BadRequestException;

public class FeignBadRequestException extends BadRequestException {

    private static final String MESSAGE = "Feign 통신 중 400에러가 발생했습니다.";

    public FeignBadRequestException() {
        super(MESSAGE);
    }
}
