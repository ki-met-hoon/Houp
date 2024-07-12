package com.example.houp.nursingfacility.department.dto;

import com.example.houp.nursingfacility.NursingFacilities;
import com.example.houp.nursingfacility.department.ClinicDepartments;
import org.jetbrains.annotations.NotNull;

public record ClinicDepartmentDto(
        Long id,
        NursingFacilities nursingFacilities,
        String codeName,
        int code
) {
    public static ClinicDepartmentDto of(NursingFacilities nursingFacilities, @NotNull String codeName, int code) {
        return new ClinicDepartmentDto(null, nursingFacilities, codeName, code);
    }

    public ClinicDepartments toEntity() {
        return ClinicDepartments.of(
                nursingFacilities,
                codeName,
                code
        );
    }
}
