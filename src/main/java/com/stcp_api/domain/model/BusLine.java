package com.stcp_api.domain.model;
import java.util.List;
public class BusLine {
    private String lineCode;
    private LineDirection directionOne;
    private LineDirection directionTwo;
    private List<BusStop> busStops;

    public BusLine(String lineCode, LineDirection directionOne, LineDirection directionTwo,List<BusStop> busStops) {
        this.lineCode = lineCode;
        this.directionOne = directionOne;
        this.directionTwo = directionTwo;
        this.busStops = busStops;
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


    public List<BusStop> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }


}
