package com.example.houp.disease.toAI.dto;

import java.util.List;
import java.util.Map;

public record PredictionResponse(
        DiseaseDetails diseaseDetails,
        Rankings rankings,
        Hospitalization hospitalization,
        Outpatient outpatient,
        String jobKind,
        String diseaseKind
) {
    public record DiseaseDetails(
            String name,
            String category,
            String description,
            int rank,
            int patients,
            int medicalCost
    ) {}

    public record Rankings(
            List<Place> places
    ) {
        public record Place(
                String name,
                int patients,
                int rank
        ) {}
    }

    public static record Hospitalization(
            double rate,
            Map<String, Integer> trend,
            double averageDays
    ) {}

    public static record Outpatient(
            double rate,
            Map<String, Integer> trend,
            double averageDays
    ) {}
}
