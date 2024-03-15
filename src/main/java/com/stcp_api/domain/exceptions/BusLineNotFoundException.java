package com.stcp_api.domain.exceptions;

public class BusLineNotFoundException extends Exception {
    public BusLineNotFoundException(String errorMessage) {
        super("Invalid bus line code: " + errorMessage);
    }
}
