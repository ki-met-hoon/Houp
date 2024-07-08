package com.example.houp.supplement;

import com.example.houp.user.UserInfos;
import jakarta.persistence.*;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Entity
public class Supplements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private UserInfos userInfos;

    @Column(length = 8, name = "supplement_name")
    @NotNull
    private String name;

    @Column(length = 128)
    @NotNull
    private String efficacy;
}
