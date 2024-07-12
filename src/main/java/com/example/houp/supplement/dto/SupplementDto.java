package com.example.houp.supplement.dto;

import com.example.houp.supplement.Supplements;
import com.example.houp.user.UserInfos;

public record SupplementDto(
        Long id,
        UserInfos userInfos,
        String name,
        String efficacy,
        String reason
) {
    public static SupplementDto of(UserInfos userInfos, String name, String efficacy, String reason) {
        return new SupplementDto(null, userInfos, name, efficacy, reason);
    }

    public Supplements toEntity() {
        return Supplements.of(
                userInfos,
                name,
                efficacy,
                reason
        );
    }
}
