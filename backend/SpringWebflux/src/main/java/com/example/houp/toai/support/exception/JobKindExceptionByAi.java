package com.example.houp.toai.support.exception;

public class JobKindExceptionByAi extends RuntimeException {

    private static final String MESSAGE = "AI로부터 받은 직종유형이 올바르지 못합니다.";

    public JobKindExceptionByAi() {
        super(MESSAGE);
    }
}
