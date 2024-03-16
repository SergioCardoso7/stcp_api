package com.stcp_api.domain.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles the exception thrown when a bus line is not found.
     * @param ex The exception thrown.
     * @return A response entity with the error message and the status code.
     */

    @ExceptionHandler(BusLineNotFoundException.class)
    public ResponseEntity<Object> handleBusLineNotFoundException(BusLineNotFoundException ex) {
        logger.warn("Bus line not found: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    /**
     * Handles the exception thrown when a bus stop is not found.
     * @param ex The exception thrown.
     * @return A response entity with the error message and the status code.
     */

    @ExceptionHandler(BusStopNotFoundException.class)
    public ResponseEntity<Object> handleBusStopNotFoundException(BusStopNotFoundException ex){
        logger.warn("Bus stop not found: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    /**
     * Handles the exception thrown when an invalid line direction is given.
     * @param ex The exception thrown.
     * @return A response entity with the error message and the status code.
     */
    @ExceptionHandler(InvalidLineDirectionException.class)
    public ResponseEntity<Object> handleInvalidLineDirectionException(InvalidLineDirectionException ex){
        logger.warn("Invalid line direction: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles the exception thrown when an internal server error occurs.
     * @param ex The exception thrown.
     * @return A response entity with the error message and the status code.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        logger.error("Internal server error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

}
