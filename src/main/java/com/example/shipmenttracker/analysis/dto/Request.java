package com.example.shipmenttracker.analysis.dto;

import com.example.shipmenttracker.chain.model.Chain;
import com.example.shipmenttracker.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private List<Chain> chains;
    private List<Product> products;
    @PositiveOrZero
    private int from = 0;
    @Positive
    private int size = 20;
}
