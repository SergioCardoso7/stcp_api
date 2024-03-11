package com.stcp_api.models.domain;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class BusLine {

    @Id
    @SequenceGenerator(name = "bus_line_sequence", sequenceName = "bus_line_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private Integer lineNumber;
    @Embedded
    private LineDirection lineDirection;

    @ManyToMany
    @JoinTable(
            name = "busline_busstop",
            joinColumns = @JoinColumn(name = "busline_id"),
            inverseJoinColumns = @JoinColumn(name = "busstop_id")
    )
    private List<BusStop> busStops;

    protected BusLine() {
    }

    public BusLine(Integer lineNumber, LineDirection lineDirection, List<BusStop> busStops) {
        this.lineNumber = lineNumber;
        this.lineDirection = lineDirection;
        this.busStops = busStops;
    }

    public BusLine(int id, Integer lineNumber, LineDirection lineDirection, List<BusStop> busStops) {
        this.id = id;
        this.lineNumber = lineNumber;
        this.lineDirection = lineDirection;
        this.busStops = busStops;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public LineDirection getLineDirections() {
        return lineDirection;
    }

    public void setLineDirections(LineDirection lineDirections) {
        this.lineDirection = lineDirections;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }
}
