package com.example.houp.toai.dto;

import java.util.List;

public record CaseExamples(
        String jobKind,
        String diseaseKind,
        List<CaseExample> caseExamples
) {

    public record CaseExample(String reviewResult, String judgmentDocument) {}
}