package com.example.shipmenttracker.finance.service;

import com.example.shipmenttracker.finance.dto.NewPriceDto;
import com.example.shipmenttracker.finance.dto.PriceShortDto;

import java.util.List;

public interface FinanceService {

    List<PriceShortDto> create(List<NewPriceDto> dtoList);

    List<PriceShortDto> update(List<PriceShortDto> dtoList);

    void delete(List<PriceShortDto> dtoList);

    List<PriceShortDto> getAll();
}
