package com.example.shipmenttracker.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualByMonthDto {

    private String chainName;
    private String categoryName;
    private Month month;
    private Long volumePromoPrice;
    private Long volumeBasePrice;
    private Double promoSharePercent;
}
