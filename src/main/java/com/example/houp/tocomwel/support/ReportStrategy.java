package com.example.houp.tocomwel.support;

import com.example.houp.tocomwel.dto.ReportToObject;

public interface ReportStrategy {

    ReportToObject getReports(ToComwelCaller caller, String serviceKey, String pageNo, String numOfRows);
}
