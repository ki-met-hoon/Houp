package com.example.houp.nursingfacility.dto;

import com.example.houp.nursingfacility.NursingFacilities;

public record NursingFacilityDto(
        Long id,
        String encryptedCode,
        String name,
        int typeCode,
        String typeCodeName,
        int cityCode,
        String cityCodeName,
        String phoneNumber,
        double coordinateX,
        double coordinateY,
        String address
//        String city,
//        String district,
//        String street,
//        String buildingNumber,
//        String detailAddress
) {
    public static NursingFacilityDto of(String encryptedCode, String name, int typeCode, String typeCodeName, int cityCode, String cityCodeName, String phoneNumber,
                                        double coordinateX, double coordinateY, String address) {
        return new NursingFacilityDto(null, encryptedCode, name, typeCode, typeCodeName, cityCode, cityCodeName, phoneNumber, coordinateX, coordinateY, address);
    }

    public NursingFacilities toEntity() {
        return NursingFacilities.of(
                encryptedCode,
                name,
                typeCode,
                typeCodeName,
                cityCode,
                cityCodeName,
                phoneNumber,
                coordinateX,
                coordinateY,
                address
        );
    }
}
