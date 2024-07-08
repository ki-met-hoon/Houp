package com.example.houp.support.excelparser.controller;

import com.example.houp.support.excelparser.service.ExcelParerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class ExcelParerController {

    private final ExcelParerService excelParerService;

    @GetMapping("/upload")
    public ResponseEntity<Void> uploadExcel(@RequestParam String fileName) {
        excelParerService.readExcel(fileName);
        return ResponseEntity.ok().build();
    }
}
