package com.example.shipmenttracker.analysis.mapper;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.dto.ActualByMonthDto;
import com.example.shipmenttracker.analysis.model.AnalysisByDate;
import com.example.shipmenttracker.analysis.model.AnalysisByMonth;

import java.time.Month;

public class ActualMapper {

    public static ActualByDateDto toByDateDto(AnalysisByDate model) {
        return new ActualByDateDto(
                model.getDate(),
                model.getProductId(),
                model.getCustomerId(),
                model.getChainName(),
                model.getVolume(),
                model.getSalesValue()
        );
    }

    public static ActualByMonthDto toByMonthDto(AnalysisByMonth model) {
        return new ActualByMonthDto(
                model.getChainName(),
                model.getCategoryName(),
                Month.of(model.getPeriod()),
                model.getVolumePromoPrice(),
                model.getVolumeBasePrice(),
                model.getPromoSharePercent()
        );
    }
}
