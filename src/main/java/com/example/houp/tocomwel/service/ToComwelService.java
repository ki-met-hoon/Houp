package com.example.houp.tocomwel.service;

import com.example.houp.support.ToComwelCaller;
import com.example.houp.toai.dto.CaseExamples;
import com.example.houp.tocomwel.dto.Decoded;
import com.example.houp.tocomwel.dto.ReportToObject;
import com.example.houp.tocomwel.dto.StrategyResult;
import com.example.houp.tocomwel.support.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ToComwelService {

    private static final String PAGENO = "1";

    private static final int MAX_REPORTS = 25;

    @Value("${feign.client.toComwel.service-key}")
    private String serviceKey;

    private final ToComwelCaller toComwelCaller;

    public CaseExamples getDiseaseDisagnosisReport(String diseaseName, String jobKind, String diseaseKind) {
        Decoded decoded = Decoded.of(diseaseName, jobKind, diseaseKind);

        return getDiseaseReportsCascade(decoded.diseaseName(), decoded.jobKind(), decoded.diseaseKind());
    }

    private CaseExamples getDiseaseReportsCascade(String diseaseName, String jobKind, String diseaseKind) {
        List<ReportStrategy> strategies = getReportStrategies(diseaseName, jobKind, diseaseKind);
        List<ReportToObject.Item> CollectReports = collectReportsFromStrategies(strategies);

        return createCaseExamples(CollectReports);
    }

    private List<ReportToObject.Item> collectReportsFromStrategies(List<ReportStrategy> strategies) {
        List<ReportToObject.Item> items = new ArrayList<>();
        int remainingCount = MAX_REPORTS;

        for (ReportStrategy strategy : strategies) {
            if (remainingCount <= 0) break;

            StrategyResult result = executeStrategy(strategy, remainingCount);
            items.addAll(result.items());
            remainingCount = result.remainingCount();
        }

        return items;
    }

    private List<ReportStrategy> getReportStrategies(String diseaseName, String jobKind, String diseaseKind) {
        return Arrays.asList(
                DiseaseJobTypeStrategy.of(diseaseName, jobKind, diseaseKind),
                JobTypeStrategy.of(jobKind, diseaseKind),
                TypeStrategy.of(diseaseKind),
                JobStrategy.of(jobKind)
        );
    }

    private StrategyResult executeStrategy(ReportStrategy strategy, int count) {
        ReportToObject reportToObject = getReportToObject(strategy, count);
        int totalCount = Integer.parseInt(reportToObject.getBody().getTotalCount());

        List<ReportToObject.Item> reports = reportToObject.getBody().getItems().getItem();

        return StrategyResult.of(reports, count - totalCount);
    }

    private ReportToObject getReportToObject(ReportStrategy strategy, int count) {
        return strategy.getReports(toComwelCaller, serviceKey, PAGENO, String.valueOf(count));
    }

    private CaseExamples createCaseExamples(List<ReportToObject.Item> items) {
        return new CaseExamples(items.stream()
                .distinct()
                .map(item -> new CaseExamples.CaseExample(item.getKinda(), item.getNoncontent()))
                .limit(MAX_REPORTS)
                .toList());
    }
}
