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

    private int age;

    @Column(length = 4)
    @NotNull
    private String gender;
}
