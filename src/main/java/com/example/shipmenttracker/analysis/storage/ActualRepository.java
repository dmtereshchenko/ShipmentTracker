package com.example.shipmenttracker.analysis.storage;

import com.example.shipmenttracker.analysis.model.Actual;
import com.example.shipmenttracker.analysis.model.AnalysisByDate;
import com.example.shipmenttracker.analysis.model.AnalysisByMonth;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActualRepository extends JpaRepository<Actual, Long> {

    @Query(value = "SELECT ch.name AS chainName, " +
            "ca.name AS categoryName, " +
            "EXTRACT(MONTH FROM date) AS period, " +
            "SUM(CASE WHEN promo = true THEN volume ELSE 0 END) AS volumePromoPrice, " +
            "SUM(CASE WHEN promo = false THEN volume ELSE 0 END) AS volumeBasePrice, " +
            "SUM(CASE WHEN promo = true THEN volume ELSE 0 END) / (SUM(CASE WHEN promo = true THEN volume ELSE 0 END) " +
            "+ SUM(CASE WHEN promo = false THEN volume ELSE 0 END)) * 100 AS promoSharePercent " +
            "FROM actual a " +
            "LEFT JOIN chain ch " +
            "LEFT JOIN category ca " +
            "GROUP BY period, chainName, categoryName",
            nativeQuery = true)
    List<AnalysisByMonth> findAnalysisByMonth();

    @Query(value = "SELECT a.date AS date, " +
            "a.product_id AS productId, " +
            "a.customer_id AS customerId, " +
            "ch.name AS chainName, " +
            "SUM(a.volume) AS volume, " +
            "SUM(a.sales_value) AS salesValue " +
            "FROM actual a " +
            "LEFT JOIN chain as ch " +
            "WHERE ch.id IN :chains " +
            "AND a.product_id IN :products " +
            "GROUP BY date, chainName, customerId",
            nativeQuery = true)
    List<AnalysisByDate> findAnalysisByDate(List<Long> chains, List<Long> products, Pageable pageable);
}
