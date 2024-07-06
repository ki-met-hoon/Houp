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

    @Column(length = 8)
    @NotNull
    private String departmentCodeName;

    private int departmentCode;
}