package com.example.shipmenttracker.analysis.service;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.dto.ActualByMonthDto;
import com.example.shipmenttracker.analysis.dto.Request;

import java.util.List;

public interface AnalysisService {

    List<ActualByMonthDto> getByMonth(int from, int size);

    List<ActualByDateDto> getByDate(Request request);
}
