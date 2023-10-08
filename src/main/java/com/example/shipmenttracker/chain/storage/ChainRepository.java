package com.example.shipmenttracker.chain.storage;

import com.example.shipmenttracker.chain.model.Chain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChainRepository extends JpaRepository<Chain, Long> {
}
