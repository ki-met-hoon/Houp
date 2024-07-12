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

    @Column(length = 64)
    private String closedOnSunday;

    @Column(length = 128)
    private String memo;

    protected FacilityDetails() {
    }

    private FacilityDetails(NursingFacilities nursingFacilities, String parkingAvailable, String lunchTime, String closedOnSunday, String memo) {
        this.nursingFacilities = nursingFacilities;
        this.parkingAvailable = parkingAvailable;
        this.lunchTime = lunchTime;
        this.closedOnSunday = closedOnSunday;
        this.memo = memo;
    }

    public static FacilityDetails of(NursingFacilities nursingFacilities, String parkingAvailable, String lunchTime, String closedOnSunday, String memo) {
        return new FacilityDetails(nursingFacilities, parkingAvailable, lunchTime, closedOnSunday, memo);
    }
}
