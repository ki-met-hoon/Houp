package com.example.houp.disease.toAI.dto;

public record UserInfoRequest(
        String name,
        String gender,
        String ageGroup,
        String job,
        String painArea,
        String painDescription
) {
}
