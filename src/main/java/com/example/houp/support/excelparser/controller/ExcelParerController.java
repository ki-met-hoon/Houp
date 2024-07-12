package com.example.houp.support.excelparser.controller;

import com.example.houp.support.excelparser.service.ExcelParerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ExcelParerController {

    private final ExcelParerService excelParerService;

    @GetMapping("/upload")
    public ResponseEntity<Void> uploadExcel() {
        excelParerService.readExcel("C:\\Users\\kwoon\\houp data.xlsx");
        return ResponseEntity.ok().build();
    }
}
