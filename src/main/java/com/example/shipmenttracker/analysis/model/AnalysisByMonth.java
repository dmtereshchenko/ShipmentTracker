package com.example.shipmenttracker.analysis.model;

public interface AnalysisByMonth {

    String getChainName();

    String getCategoryName();

    Integer getPeriod();

    Long getVolumePromoPrice();

    Long getVolumeBasePrice();

    Double getPromoSharePercent();
}
