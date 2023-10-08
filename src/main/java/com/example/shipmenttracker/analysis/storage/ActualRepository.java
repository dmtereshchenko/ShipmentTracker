package com.example.shipmenttracker.analysis.storage;

import com.example.shipmenttracker.analysis.model.Actual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualRepository extends JpaRepository<Actual, Long>, CustomRepository {

}
