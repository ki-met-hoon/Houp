package com.example.houp.tocomwel.support.exception;

import com.example.houp.advice.BadRequestException;

public class DiseaseKindExceptionByUser extends BadRequestException {

    private static final String MESSAGE = "유저로부터 받은 질병유형이 올바르지 못합니다.";

    public DiseaseKindExceptionByUser() {
        super(MESSAGE);
    }
}
