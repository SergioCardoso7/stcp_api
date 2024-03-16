package com.stcp_api.domain.exceptions;

/**
 * Exception thrown when a bus line is not found.
 */
public class BusLineNotFoundException extends Exception {
    public BusLineNotFoundException(String errorMessage) {
        super("Invalid bus line code: " + errorMessage);
    }
}
