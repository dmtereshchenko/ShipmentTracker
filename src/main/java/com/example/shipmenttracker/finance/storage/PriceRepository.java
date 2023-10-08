package com.example.shipmenttracker.finance.storage;

import com.example.shipmenttracker.finance.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    Price findPriceByChainIdAndProductId(Long chainId, Long productId);
}
