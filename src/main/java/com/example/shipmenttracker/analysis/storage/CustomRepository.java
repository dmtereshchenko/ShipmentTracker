package com.example.shipmenttracker.analysis.storage;

import com.example.shipmenttracker.analysis.dto.Request;
import com.example.shipmenttracker.analysis.model.Actual;

import java.util.List;

public interface CustomRepository {

    List<Actual> findAllByRequest(Request request);
}
