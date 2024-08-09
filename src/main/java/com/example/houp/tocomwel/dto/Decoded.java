package com.example.houp.tocomwel.dto;

import com.example.houp.support.UrlUtility;

public record Decoded(
        String diseaseName,
        String jobKind,
        String diseaseKind
) {

    public static Decoded of(String diseaseName, String jobKind, String diseaseKind) {
        String decodedDiseaseName = UrlUtility.decode(diseaseName);
        String decodedJobKind = UrlUtility.decode(jobKind);
        String decodedDiseaseKind = UrlUtility.decode(diseaseKind);

        return new Decoded(decodedDiseaseName, decodedJobKind, decodedDiseaseKind);
    }
}
