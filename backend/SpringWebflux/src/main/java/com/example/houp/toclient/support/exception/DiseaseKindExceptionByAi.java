package com.example.houp.toclient.support.exception;

public class DiseaseKindExceptionByAi extends RuntimeException {

    private static final String MESSAGE = "AI로부터 받은 질병유형이 올바르지 못합니다.";

    public DiseaseKindExceptionByAi() {
        super(MESSAGE);
    }
}
