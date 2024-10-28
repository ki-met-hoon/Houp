package com.example.houp.toclient.support.exception;

public class JsonParsingException extends RuntimeException{

    private static final String MESSAGE = "AI의 답변을 질병 판정서로 변환하는 과정에서 오류가 발생했습니다.";

    public JsonParsingException() {
        super(MESSAGE);
    }
}
