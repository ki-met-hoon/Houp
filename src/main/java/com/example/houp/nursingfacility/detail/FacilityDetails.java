package com.example.houp.nursingfacility.detail;

import com.example.houp.nursingfacility.NursingFacilities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Entity
public class FacilityDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private NursingFacilities nursingFacilities;

    @Column(length = 8)
    @NotNull
    private String parkingAvailable;

    @Column(length = 16)
    private String lunchTime;

    @Column(length = 8)
    private String closedOnSunday;

    @Column(length = 8)
    private String closedOnHoliday;
}
