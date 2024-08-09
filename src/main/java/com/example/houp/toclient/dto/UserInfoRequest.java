package com.example.houp.toclient.dto;

public record UserInfoRequest(
        String name,
        String gender,
        String ageGroup,
        String job,
        String painArea,
        String painDescription
) {
}
