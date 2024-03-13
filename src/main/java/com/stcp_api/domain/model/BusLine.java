package com.stcp_api.domain.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class BusLine {

    @Id
    @SequenceGenerator(name = "bus_line_sequence", sequenceName = "bus_line_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String lineCode;
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

    public BusLine(String lineCode, LineDirection lineDirection, List<BusStop> busStops) {
        this.lineCode = lineCode;
        this.lineDirection = lineDirection;
        this.busStops = busStops;
    }

    public BusLine(int id, String lineCode, LineDirection lineDirection, List<BusStop> busStops) {
        this.id = id;
        this.lineCode = lineCode;
        this.lineDirection = lineDirection;
        this.busStops = busStops;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public LineDirection getLineDirection() {
        return lineDirection;
    }

    public void setLineDirection(LineDirection lineDirection) {
        this.lineDirection = lineDirection;
    }


    public List<BusStop> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<BusStop> busStops) {
        this.busStops = busStops;
    }

    @Override
    public String toString() {
        return "BusLine{" +
                "lineCode='" + lineCode + '\'' +
                ", lineDirection=" + lineDirection +
                '}';
    }
}
