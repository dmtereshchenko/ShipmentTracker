package com.example.shipmenttracker.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceShortDto {

    private Long id;
    private Long chainId;
    private Long productId;
    private Double regularPricePerUnit;
}
