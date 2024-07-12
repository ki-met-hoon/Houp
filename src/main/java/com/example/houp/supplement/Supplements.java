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

    @Column(length = 24, name = "supplement_name")
    @NotNull
    private String name;

    @Column(length = 512)
    @NotNull
    private String efficacy;

    @Column(length = 512)
    @NotNull
    private String reason;

    protected Supplements() {
    }

    private Supplements(UserInfos userInfos, @NotNull String name, @NotNull String efficacy, @NotNull String reason) {
        this.userInfos = userInfos;
        this.name = name;
        this.efficacy = efficacy;
        this.reason = reason;
    }

    public static Supplements of(UserInfos userInfos, String name, String efficacy, String reason) {
        return new Supplements(userInfos, name, efficacy, reason);
    }
}
