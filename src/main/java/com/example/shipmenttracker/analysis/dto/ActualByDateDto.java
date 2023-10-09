package com.example.shipmenttracker.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActualByDateDto {

    private LocalDate date;
    private Long productId;
    private Long customerId;
    private String chainName;
    private Long volume;
    private Double salesValue;
}
