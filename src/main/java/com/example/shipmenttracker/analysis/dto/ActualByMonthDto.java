package com.example.shipmenttracker.analysis.dto;

import com.example.shipmenttracker.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualByMonthDto {

    private String chainName;
    private Category category;
    private Month month;
    private Long volumeBasePrice;
    private Long volumePromoPrice;
    private float promoShare;
}
