package com.example.houp.user;

import jakarta.persistence.*;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Entity
public class UserInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8)
    @NotNull
    private String ageGroup;

    @Column(length = 8)
    @NotNull
    private String job;

    @Column(length = 4)
    @NotNull
    private String gender;

    protected UserInfos() {
    }

    private UserInfos(@NotNull String ageGroup, @NotNull String job, @NotNull String gender) {
        this.ageGroup = ageGroup;
        this.job = job;
        this.gender = gender;
    }

    public static UserInfos of(String ageGroup, String job, String gender) {
        return new UserInfos(ageGroup, job, gender);
    }
}
