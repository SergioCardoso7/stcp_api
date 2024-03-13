package com.stcp_api.domain.model;

public class LineDirection {

    private final int directionCode;

    private final String directionEndStop;

    public LineDirection(int directionCode, String directionEndStop) {
        this.directionCode = directionCode;
        this.directionEndStop = directionEndStop;
    }

    public int getDirectionCode() {
        return directionCode;
    }

    public String getDirectionEndStop() {
        return directionEndStop;
    }


}
