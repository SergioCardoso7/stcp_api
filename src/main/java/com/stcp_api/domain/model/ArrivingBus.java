package com.stcp_api.domain.model;

import java.time.LocalTime;

public class ArrivingBus {
    private String busLineCode;
    private String endBusStopName;
    private LocalTime estimatedTimeOfArrival;
    private String waitingTime;

    public ArrivingBus(String busLineCode, String endBusStopName, LocalTime estimatedTimeOfArrival, String waitingTime) {
        this.busLineCode = busLineCode;
        this.endBusStopName = endBusStopName;
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
        this.waitingTime = waitingTime;
    }

    public ArrivingBus(String busLineCode, String endBusStopName, String waitingTime) {
        this.busLineCode = busLineCode;
        this.endBusStopName = endBusStopName;
        this.waitingTime = waitingTime;
    }


    public String getBusLineCode() {
        return busLineCode;
    }

    public void setBusLineCode(String busLineCode) {
        this.busLineCode = busLineCode;
    }

    public String getEndBusStopName() {
        return endBusStopName;
    }

    public void setEndBusStopName(String endBusStopName) {
        this.endBusStopName = endBusStopName;
    }

    public LocalTime getEstimatedTimeOfArrival() {
        return estimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(LocalTime estimatedTimeOfArrival) {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }
}
