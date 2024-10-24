package com.example.houp.toclient.support.exception;

public class DiseaseResponseBySeverException extends RuntimeException {

    private static final String MESSAGE = "질병 진단 API 요청 중 서버로부터 오류가 발생했습니다.";

    public DiseaseResponseBySeverException() {
        super(MESSAGE);
    }
}
