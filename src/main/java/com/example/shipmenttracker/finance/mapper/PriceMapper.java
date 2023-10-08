package com.example.shipmenttracker.finance.mapper;

import com.example.shipmenttracker.chain.model.Chain;
import com.example.shipmenttracker.finance.dto.NewPriceDto;
import com.example.shipmenttracker.finance.dto.PriceShortDto;
import com.example.shipmenttracker.finance.model.Price;
import com.example.shipmenttracker.product.model.Product;

public class PriceMapper {

    public static Price toPrice(NewPriceDto dto, Chain chain, Product product) {
        return new Price(
                null,
                chain,
                product,
                dto.getRegularPricePerUnit()
        );
    }

    public static Price toPrice(PriceShortDto dto, Chain chain, Product product) {
        return new Price(
                dto.getId(),
                chain,
                product,
                dto.getRegularPricePerUnit()
        );
    }

    public static PriceShortDto toShortDto(Price price) {
        return new PriceShortDto(
                price.getId(),
                price.getChain().getId(),
                price.getProduct().getId(),
                price.getRegularPricePerUnit()
        );
    }
}
