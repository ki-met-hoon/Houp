package com.example.houp.nursingfacility;

import jakarta.persistence.*;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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

    @Column(length = 256)
    @NotNull
    private String address;

//    @Column(length = 8)
//    @NotNull
//    private String city;
//
//    @Column(length = 8)
//    @NotNull
//    private String district;
//
//    @Column(length = 16)
//    @NotNull
//    private String street;
//
//    @Column(length = 8)
//    @NotNull
//    private String buildingNumber;
//
//    @Column(length = 64)
//    private String detailAddress;

    protected NursingFacilities() {
    }

    private NursingFacilities(@NotNull String encryptedCode, @NotNull String name, int typeCode, @NotNull String typeCodeName, int cityCode,
                              @NotNull String cityCodeName, @NotNull String phoneNumber, double coordinateX, double coordinateY, @NotNull String address) {
        this.encryptedCode = encryptedCode;
        this.name = name;
        this.typeCode = typeCode;
        this.typeCodeName = typeCodeName;
        this.cityCode = cityCode;
        this.cityCodeName = cityCodeName;
        this.phoneNumber = phoneNumber;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.address = address;
    }

    public static NursingFacilities of(String encryptedCode, String name, int typeCode, String typeCodeName, int cityCode, String cityCodeName, String phoneNumber,
                                       double coordinateX, double coordinateY, String address) {
        return new NursingFacilities(encryptedCode, name, typeCode, typeCodeName, cityCode, cityCodeName, phoneNumber, coordinateX, coordinateY, address);
    }
}