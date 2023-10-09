package com.example.shipmenttracker.analysis.service;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.dto.ActualByMonthDto;
import com.example.shipmenttracker.analysis.mapper.ActualMapper;
import com.example.shipmenttracker.analysis.storage.ActualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisServiceImpl implements AnalysisService {

    private final ActualRepository actualRepository;

    @Override
    public List<ActualByMonthDto> getByMonth(int from, int size) {
        return actualRepository.findAnalysisByMonth()
                .stream()
                .map(ActualMapper::toByMonthDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActualByDateDto> getByDate(List<Long> chains, List<Long> products, int from, int size) {
        return actualRepository.findAnalysisByDate(chains, products, PageRequest.of(from / size, size))
                .stream()
                .map(ActualMapper::toByDateDto)
                .collect(Collectors.toList());
    }
}
