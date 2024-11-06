package com.example.houp.toclient.support.exception;

import com.example.houp.advice.BadRequestException;

public class JudgeDocRequestException extends BadRequestException {

    private static final String MESSAGE = "산재 보험 처리 확률 API 요청 중 AI에게 전달하는 Case 정보에 오류가 발생했습니다.";

    public JudgeDocRequestException() {
        super(MESSAGE);
    }
}
