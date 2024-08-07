package com.example.houp.disease.toai.dto;

public record UserInfoRequest(
        String name,
        String gender,
        String ageGroup,
        String job,
        String painArea,
        String painDescription
) {
}
