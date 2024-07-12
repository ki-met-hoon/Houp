package com.example.houp.nursingfacility.detail.dto;

import com.example.houp.nursingfacility.NursingFacilities;
import com.example.houp.nursingfacility.detail.FacilityDetails;

public record FacilityDetailDto(
        Long id,
        NursingFacilities nursingFacilities,
        String parkingAvailable,
        String lunchTime,
        String closedOnSunday,
        String memo
) {
    public static FacilityDetailDto of(NursingFacilities nursingFacilities, String parkingAvailable, String lunchTime, String closedOnSunday, String memo) {
        return new FacilityDetailDto(null, nursingFacilities, parkingAvailable, lunchTime, closedOnSunday, memo);
    }


    public FacilityDetails toEntity() {
        return FacilityDetails.of(
                nursingFacilities,
                parkingAvailable,
                lunchTime,
                closedOnSunday,
                memo
        );
    }
}
