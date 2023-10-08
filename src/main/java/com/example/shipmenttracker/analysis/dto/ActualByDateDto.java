package com.example.shipmenttracker.analysis.dto;

import com.example.shipmenttracker.customer.model.Customer;
import com.example.shipmenttracker.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualByDateDto {

    private LocalDate date;
    private Product product;
    private Customer customer;
    private String chainName;
    private Long volume;
    private Double salesValue;
}
