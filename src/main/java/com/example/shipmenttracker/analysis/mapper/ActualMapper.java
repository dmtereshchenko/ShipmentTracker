package com.example.shipmenttracker.analysis.mapper;

import com.example.shipmenttracker.analysis.dto.ActualByDateDto;
import com.example.shipmenttracker.analysis.model.Actual;

public class ActualMapper {

    public static ActualByDateDto toByDateDto(Actual actual) {
        return new ActualByDateDto(
                actual.getDate(),
                actual.getProduct(),
                actual.getCustomer(),
                actual.getChain().getName(),
                actual.getVolume(),
                actual.getSalesValue()
        );
    }
}
