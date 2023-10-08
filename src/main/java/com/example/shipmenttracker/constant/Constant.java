package com.example.shipmenttracker.constant;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class Constant {

    public static final String DTFormat = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(Constant.DTFormat);
}
