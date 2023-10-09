package com.example.shipmenttracker.analysis.model;

import java.time.LocalDate;

public interface AnalysisByDate {

    LocalDate getDate();

    Long getProductId();

    Long getCustomerId();

    String getChainName();

    Long getVolume();

    Double getSalesValue();
}
