package com.example.houp.nursingfacility.hour.dto;

import com.example.houp.nursingfacility.NursingFacilities;
import com.example.houp.nursingfacility.hour.ClinicHours;

public record ClinicHourDto(
        Long id,
        NursingFacilities nursingFacilities,
        String dayOfWeek,
        int startTime,
        int endTime
) {
    public static ClinicHourDto of(NursingFacilities nursingFacilities, int day, int startTime, int endTime) {
        String[] daysOfWeek = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};

        if (day >= 0 && day < daysOfWeek.length) {
            return new ClinicHourDto(null, nursingFacilities, daysOfWeek[day], startTime, endTime);
        } else {
            throw new IllegalArgumentException("Invalid day value: " + day);
        }
    }

    public ClinicHours toEntity() {
        return ClinicHours.of(
                nursingFacilities,
                dayOfWeek,
                startTime,
                endTime
        );
    }
}
