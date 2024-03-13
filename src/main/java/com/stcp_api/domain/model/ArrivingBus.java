package com.stcp_api.domain.model;

import java.time.LocalTime;

public class ArrivingBus {
    private BusLine busLine;
    private BusStop busStop;
    private LocalTime estimatedTimeOfArrival;

    private LocalTime waitingTime;


    public ArrivingBus(BusLine busLine, BusStop busStop, LocalTime estimatedTimeOfArrival, LocalTime waitingTime) {
        this.busLine = busLine;
        this.busStop = busStop;
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
        this.waitingTime = waitingTime;
    }

    public BusLine getBusLine() {
        return busLine;
    }

    public void setBusLine(BusLine busLine) {
        this.busLine = busLine;
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public LocalTime getEstimatedTimeOfArrival() {
        return estimatedTimeOfArrival;
    }

    public void setEstimatedTimeOfArrival(LocalTime estimatedTimeOfArrival) {
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
    }

    public LocalTime getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(LocalTime waitingTime) {
        this.waitingTime = waitingTime;
    }
}
