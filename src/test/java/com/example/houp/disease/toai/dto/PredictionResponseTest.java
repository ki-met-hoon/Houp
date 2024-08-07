package com.example.houp.disease.toai.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PredictionResponseTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJsonToPredictionResponse() throws Exception {
        String json = """
                {
                  "diseaseDetails": {
                    "name": "급성 기관지염",
                    "category": "호흡기 질병",
                    "description": "급성 기관지염은 기관지에 염증이 생겨 기침과 가래를 동반하는...",
                    "rank": 2,
                    "patients": 1736512,
                    "medicalCost": 16090
                  },
                  "rankings": {
                    "places": [
                      {
                        "name": "치주염 및 치주질환",
                        "patients": 2332266,
                        "rank": 1
                      },
                      {
                        "name": "급성 기관지염",
                        "patients": 1736512,
                        "rank": 2
                      },
                      {
                        "name": "혈관 운동성 및 알레르기성 비염",
                        "patients": 1418573,
                        "rank": 3
                      }
                    ]
                  },
                  "hospitalization": {
                    "rate": 0.12,
                    "trend": {
                      "2020": 1630,
                      "2021": 747,
                      "2022": 1715,
                      "2023": 2118
                    },
                    "averageDays": 4.06
                  },
                  "outpatient": {
                    "rate": 99.88,
                    "trend": {
                      "2020": 1040739,
                      "2021": 566982,
                      "2022": 1204132,
                      "2023": 1736512
                    },
                    "averageDays": 1.77
                  },
                  "jobKind": "(특수직종)[손보||대리점||우체국]보험설계사",
                  "diseaseKind": "근골격계질환 (척추질환)"
                }
                """;

        PredictionResponse response = objectMapper.readValue(json, PredictionResponse.class);

        assertNotNull(response);
        assertNotNull(response.diseaseDetails());
        assertEquals("급성 기관지염", response.diseaseDetails().name());
        assertEquals("호흡기 질병", response.diseaseDetails().category());
        assertEquals(2, response.diseaseDetails().rank());
        assertEquals(1736512, response.diseaseDetails().patients());
        assertEquals(16090, response.diseaseDetails().medicalCost());

        assertNotNull(response.rankings());
        assertNotNull(response.rankings().places());
        assertEquals(3, response.rankings().places().size());

        PredictionResponse.Rankings.Place firstPlace = response.rankings().places().get(0);
        assertEquals("치주염 및 치주질환", firstPlace.name());
        assertEquals(2332266, firstPlace.patients());
        assertEquals(1, firstPlace.rank());

        PredictionResponse.Rankings.Place secondPlace = response.rankings().places().get(1);
        assertEquals("급성 기관지염", secondPlace.name());
        assertEquals(1736512, secondPlace.patients());
        assertEquals(2, secondPlace.rank());

        PredictionResponse.Rankings.Place thirdPlace = response.rankings().places().get(2);
        assertEquals("혈관 운동성 및 알레르기성 비염", thirdPlace.name());
        assertEquals(1418573, thirdPlace.patients());
        assertEquals(3, thirdPlace.rank());

        assertNotNull(response.hospitalization());
        assertEquals(0.12, response.hospitalization().rate());
        assertEquals(4.06, response.hospitalization().averageDays());
        assertEquals(1630, response.hospitalization().trend().get("2020"));
        assertEquals(747, response.hospitalization().trend().get("2021"));
        assertEquals(1715, response.hospitalization().trend().get("2022"));
        assertEquals(2118, response.hospitalization().trend().get("2023"));

        assertNotNull(response.outpatient());
        assertEquals(99.88, response.outpatient().rate());
        assertEquals(1.77, response.outpatient().averageDays());
        assertEquals(1040739, response.outpatient().trend().get("2020"));
        assertEquals(566982, response.outpatient().trend().get("2021"));
        assertEquals(1204132, response.outpatient().trend().get("2022"));
        assertEquals(1736512, response.outpatient().trend().get("2023"));

        assertEquals("(특수직종)[손보||대리점||우체국]보험설계사", response.jobKind());
        assertEquals("근골격계질환 (척추질환)", response.diseaseKind());
    }
}
