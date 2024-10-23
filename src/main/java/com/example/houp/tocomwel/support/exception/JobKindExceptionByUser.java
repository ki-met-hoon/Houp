package com.example.houp.tocomwel.support.exception;

import com.example.houp.advice.BadRequestException;

public class JobKindExceptionByUser extends BadRequestException {

    private static final String MESSAGE = "유저로부터 받은 직종유형이 올바르지 못합니다.";

    public JobKindExceptionByUser() {
        super(MESSAGE);
    }
}
