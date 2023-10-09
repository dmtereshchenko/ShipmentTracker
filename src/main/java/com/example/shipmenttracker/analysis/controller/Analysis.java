package com.example.shipmenttracker.analysis.controller;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.dto.ActualByMonthDto;
import com.example.shipmenttracker.analysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/analysis")
public class Analysis {

    private final AnalysisService service;

    @GetMapping("/month")
    List<ActualByMonthDto> getByMonth(@RequestParam(defaultValue = "0") int from,
                                      @RequestParam(defaultValue = "20") int size) {
        log.info("Получен запрос GET /analysis/month");
        return service.getByMonth(from, size);
    }

    @GetMapping("/date")
    List<ActualByDateDto> getByDate(@RequestParam(required = false) List<Long> chains,
                                    @RequestParam(required = false) List<Long> products,
                                    @RequestParam(defaultValue = "0") int from,
                                    @RequestParam(defaultValue = "20") int size) {
        log.info("Получен запрос GET /analysis/date");
        return service.getByDate(chains, products, from, size);
    }
}
