package com.example.houp.toclient.support.exception;

public class DiseaseResponseByAiException extends RuntimeException {

    private static final String MESSAGE = "질병 진단 API 요청 중 AI로부터 응답에 오류가 발생했습니다.";

    public DiseaseResponseByAiException() {
        super(MESSAGE);
    }
}
