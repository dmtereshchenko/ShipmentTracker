package com.example.shipmenttracker.exception;

import com.example.shipmenttracker.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({ChainNotFoundException.class, ProductNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(ChainNotFoundException e) {
        log.warn(e.getMessage());
        return new ApiError(e.getMessage(), "Данные не найдены", HttpStatus.NOT_FOUND.getReasonPhrase(), LocalDateTime.now().format(Constant.FORMATTER));
    }
}
