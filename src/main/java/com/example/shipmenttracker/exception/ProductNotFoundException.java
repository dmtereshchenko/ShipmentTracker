package com.example.shipmenttracker.exception;

import java.util.NoSuchElementException;

public class ProductNotFoundException extends NoSuchElementException {

    public ProductNotFoundException(Long id) {
        super("Товар с Id " + id + " не найден");
    }
}
