package com.example.shipmenttracker.analysis.service;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.dto.ActualByMonthDto;

import java.util.List;

public interface AnalysisService {

    List<ActualByMonthDto> getByMonth(int from, int size);

    List<ActualByDateDto> getByDate(List<Long> chains, List<Long> products, int from, int size);
}
