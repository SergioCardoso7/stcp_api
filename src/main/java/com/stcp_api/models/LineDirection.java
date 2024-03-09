package com.stcp_api.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class LineDirection {

    private int directionCode;

    private String directionEndStop;


    protected LineDirection(){

    }


    public LineDirection(int directionCode, String directionEndStop) {
        this.directionCode = directionCode;
        this.directionEndStop = directionEndStop;
    }

    public int getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(int directionCode) {
        this.directionCode = directionCode;
    }

    public String getDirectionEndStop() {
        return directionEndStop;
    }

    public void setDirectionEndStop(String directionEndStop) {
        this.directionEndStop = directionEndStop;
    }
}
