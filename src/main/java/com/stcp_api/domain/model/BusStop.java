package com.stcp_api.domain.model;

import java.awt.geom.Point2D;
import java.util.List;


public class BusStop {
    private String code;
    private String name;
    private String zone;
    private Point2D location;
    private List<ArrivingBus> arrivingBuses;

    public BusStop(String code, String name, String zone, Point2D location, List<ArrivingBus> arrivingBuses) {
        this.code = code;
        this.name = name;
        this.zone = zone;
        this.location = location;
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

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public List<ArrivingBus> getIncomingBuses() {
        return arrivingBuses;
    }

    public void setIncomingBuses(List<ArrivingBus> arrivingBuses) {
        this.arrivingBuses = arrivingBuses;
    }
}
