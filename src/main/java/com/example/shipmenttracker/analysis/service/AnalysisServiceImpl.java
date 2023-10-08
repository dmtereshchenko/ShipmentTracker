package com.example.shipmenttracker.analysis.service;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.dto.ActualByMonthDto;
import com.example.shipmenttracker.analysis.dto.Request;
import com.example.shipmenttracker.analysis.mapper.ActualMapper;
import com.example.shipmenttracker.analysis.storage.ActualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisServiceImpl implements AnalysisService {

    private final ActualRepository repository;

    @Override
    public List<ActualByMonthDto> getByMonth(int from, int size) {
        return null;
    }

    @Override
    public List<ActualByDateDto> getByDate(Request request) {
        return repository.findAllByRequest(request)
                .stream()
                .map(ActualMapper::toByDateDto)
                .collect(Collectors.toList());
    }
}
