package com.stcp_api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BusStopNotFoundException extends Exception {
    public BusStopNotFoundException(String errorMessage) {
        super("Invalid bus stop code: " + errorMessage);
    }
}
