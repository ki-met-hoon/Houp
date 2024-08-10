package com.example.houp.support.exception;

import com.example.houp.advice.NotFoundException;

public class FeignNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Feign 통신 중 404에러가 발생했습니다.";

    public FeignNotFoundException() {
        super(MESSAGE);
    }
}
