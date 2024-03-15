package com.stcp_api.domain.exceptions;

public class BusStopNotFoundException extends Exception {
    public BusStopNotFoundException(String errorMessage) {
        super("Invalid bus stop code: " + errorMessage);
    }
}
