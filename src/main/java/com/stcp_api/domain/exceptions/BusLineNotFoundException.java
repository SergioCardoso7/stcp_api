package com.stcp_api.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class BusLineNotFoundException extends Exception {
    public BusLineNotFoundException(String errorMessage) {
        super("Invalid bus line code: " + errorMessage);
    }
}
