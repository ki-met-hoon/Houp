package com.example.houp.toclient.support.exception;

public class JudgeDocResponseBySeverException extends RuntimeException{

    private static final String MESSAGE = "산재 보험 처리 확률 API 요청 중 서버로부터 오류가 발생했습니다.";

    public JudgeDocResponseBySeverException() {
        super(MESSAGE);
    }
}
