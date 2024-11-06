package com.example.houp.toclient.support.exception;

public class JudgeDocResponseByAiException extends RuntimeException {

    private static final String MESSAGE = "산재 보험 처리 확률 API 요청 중 AI로부터 오류가 발생했습니다.";

    public JudgeDocResponseByAiException() {
        super(MESSAGE);
    }
}
