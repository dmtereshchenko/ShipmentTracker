package com.example.shipmenttracker.exception;

import java.util.NoSuchElementException;

public class ChainNotFoundException extends NoSuchElementException {

    public ChainNotFoundException(Long id) {
        super("Торговая сеть с Id " + id + "не найдена");
    }
}
