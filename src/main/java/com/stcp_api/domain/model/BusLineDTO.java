package com.stcp_api.domain.model;

public class BusLineDTO {

    private String lineCode;
    private LineDirection directionOne;
    private LineDirection directionTwo;

    public BusLineDTO(String lineCode, LineDirection directionOne, LineDirection directionTwo) {
        this.lineCode = lineCode;
        this.directionOne = directionOne;
        this.directionTwo = directionTwo;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public LineDirection getDirectionOne() {
        return directionOne;
    }

    public void setDirectionOne(LineDirection directionOne) {
        this.directionOne = directionOne;
    }

    public LineDirection getDirectionTwo() {
        return directionTwo;
    }

    public void setDirectionTwo(LineDirection directionTwo) {
        this.directionTwo = directionTwo;
    }
}
