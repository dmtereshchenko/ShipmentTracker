package com.example.shipmenttracker.finance.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPriceDto {

    @NotNull
    private Long chainId;
    @NotNull
    private Long productId;
    @NotNull
    private Double regularPricePerUnit;
}
