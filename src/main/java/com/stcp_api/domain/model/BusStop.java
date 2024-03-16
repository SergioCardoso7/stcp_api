package com.stcp_api.domain.model;

import java.awt.geom.Point2D;
import java.util.List;


public class BusStop {
    private String code;
    private String name;
    private String zone;
    private Point2D coordinates;
    private List<ArrivingBus> arrivingBuses;

    public BusStop(String code, String name, String zone, Point2D coordinates, List<ArrivingBus> arrivingBuses) {
        this.code = code;
        this.name = name;
        this.zone = zone;
        this.coordinates = coordinates;
        this.arrivingBuses = arrivingBuses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D coordinates) {
        this.coordinates = coordinates;
    }

    public List<ArrivingBus> getIncomingBuses() {
        return arrivingBuses;
    }

    public void setIncomingBuses(List<ArrivingBus> arrivingBuses) {
        this.arrivingBuses = arrivingBuses;
    }
}
