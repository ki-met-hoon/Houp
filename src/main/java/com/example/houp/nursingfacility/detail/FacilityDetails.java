package com.example.houp.nursingfacility.detail;

import com.example.houp.nursingfacility.NursingFacilities;
import jakarta.persistence.*;
import lombok.Getter;

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
    private String parkingAvailable;

    @Column(length = 16)
    private String lunchTime;

    @Column(length = 8)
    private String closedOnSunday;

    @Column(length = 128)
    private String memo;
}
