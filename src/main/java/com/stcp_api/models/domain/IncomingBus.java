package com.stcp_api.models.domain;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class IncomingBus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private BusLine busLine;

    @OneToOne
    private BusStop busStop;
    private LocalTime estimatedTimeOfArrival;

    private LocalTime waitingTime;

    protected IncomingBus() {

    }

    public IncomingBus(BusLine busLine, BusStop busStop, LocalTime estimatedTimeOfArrival, LocalTime waitingTime) {
        this.busLine = busLine;
        this.busStop = busStop;
        this.estimatedTimeOfArrival = estimatedTimeOfArrival;
        this.waitingTime = waitingTime;
    }

    public IncomingBus(int id, BusLine busLine, BusStop busStop, LocalTime estimatedTimeOfArrival, LocalTime waitingTime) {
        this.id = id;
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
