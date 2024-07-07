package com.example.houp.nursingfacility;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import lombok.Getter;

@Getter
@Entity
public class NursingFacilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128)
    @NotNull
    private String encryptedCode;

    @Column(length = 64, name = "facility_name")
    @NotNull
    private String name;

    private int typeCode;

    @Column(length = 16)
    @NotNull
    private String typeCodeName;

    private int cityCode;

    @Column(length = 8)
    @NotNull
    private String cityCodeName;

    @Column(length = 16)
    @NotNull
    private String phoneNumber;

    private double coordinateX;

    private double coordinateY;

    @Column(length = 8)
    @NotNull
    private String city;

    @Column(length = 8)
    @NotNull
    private String district;

    @Column(length = 16)
    @NotNull
    private String street;

    @Column(length = 8)
    @NotNull
    private String buildingNumber;

    @Column(length = 32)
    private String detailAddress;
}