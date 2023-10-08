package com.example.shipmenttracker.finance.controller;

import com.example.shipmenttracker.finance.dto.NewPriceDto;
import com.example.shipmenttracker.finance.dto.PriceShortDto;
import com.example.shipmenttracker.finance.service.FinanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/finance")
public class Finance {

    private final FinanceService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    List<PriceShortDto> create(@Valid @RequestBody List<NewPriceDto> list) {
        log.info("Получен запрос POST /finance/");
        return service.create(list);
    }

    @PatchMapping
    List<PriceShortDto> update(@Valid @RequestBody List<PriceShortDto> list) {
        log.info("Получен запрос PATCH /finance");
        return service.update(list);
    }

    @DeleteMapping
    void delete(@RequestBody List<PriceShortDto> list) {
        log.info("Получен запрос DELETE /finance");
        service.delete(list);
    }

    @GetMapping
    List<PriceShortDto> getAll() {
        return service.getAll();
    }
}
