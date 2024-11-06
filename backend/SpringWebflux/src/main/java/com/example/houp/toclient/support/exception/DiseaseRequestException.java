package com.example.houp.toclient.support.exception;

import com.example.houp.advice.BadRequestException;

public class DiseaseRequestException extends BadRequestException {

    private static final String MESSAGE = "질병 진단 API 요청 중 전달하는 유저 정보에 오류가 발생했습니다.";

    public DiseaseRequestException() {
        super(MESSAGE);
    }
}
