package com.example.houp.nursingfacility.department;

import com.example.houp.nursingfacility.NursingFacilities;
import jakarta.persistence.*;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
@Entity
public class ClinicDepartments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private NursingFacilities nursingFacilities;

    @Column(length = 8, name = "department_code")
    @NotNull
    private String codeName;

    @Column(name = "department_code_name")
    private int code;

    protected ClinicDepartments() {
    }

    private ClinicDepartments(NursingFacilities nursingFacilities, @NotNull String codeName, int code) {
        this.nursingFacilities = nursingFacilities;
        this.codeName = codeName;
        this.code = code;
    }

    public static ClinicDepartments of(NursingFacilities nursingFacilities, @NotNull String codeName, int code) {
        return new ClinicDepartments(nursingFacilities, codeName, code);
    }
}