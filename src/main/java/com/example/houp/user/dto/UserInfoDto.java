package com.example.houp.user.dto;

import com.example.houp.user.UserInfos;

public record UserInfoDto(
        Long id,
        String ageGroup,
        String job,
        String gender
) {
    public static UserInfoDto of(String ageGroup, String jop, String gender) {
        return new UserInfoDto(null, ageGroup, jop, gender);
    }

    public UserInfos toEntity() {
        return UserInfos.of(
                ageGroup,
                job,
                gender
        );
    }
}
