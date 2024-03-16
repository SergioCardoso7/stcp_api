package com.stcp_api.domain.exceptions;

/**
 * Exception thrown when a bus line is not found.
 */

public class BusStopNotFoundException extends Exception {
    public BusStopNotFoundException(String errorMessage) {
        super("Invalid bus stop code: " + errorMessage);
    }
}
