package com.example.houp.nursingfacility.hour;

import com.example.houp.nursingfacility.NursingFacilities;
import jakarta.persistence.*;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Entity
public class ClinicHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private NursingFacilities nursingFacilities;

    @Column(length = 8)
    @NotNull
    private String dayOfWeek;

    private int startTime;

    private int endTime;

    protected ClinicHours() {
    }

    private ClinicHours(NursingFacilities nursingFacilities, @NotNull String dayOfWeek, int startTime, int endTime) {
        this.nursingFacilities = nursingFacilities;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static ClinicHours of(NursingFacilities nursingFacilities, @NotNull String dayOfWeek, int startTime, int endTime) {
        return new ClinicHours(nursingFacilities, dayOfWeek, startTime, endTime);
    }
}