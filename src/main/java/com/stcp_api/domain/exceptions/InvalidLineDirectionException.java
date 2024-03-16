package com.stcp_api.domain.exceptions;

public class InvalidLineDirectionException extends Exception {
    public InvalidLineDirectionException(String errorMessage) {
        super("Line direction should be 0 or 1, invalid direction code: " + errorMessage);
    }
}
