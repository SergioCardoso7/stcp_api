package com.stcp_api.domain.model;

import java.awt.geom.Point2D;

public class BusStopDTO {
    private String code;
    private String name;
    private String zone;
    private Point2D.Double coordinates;


    public BusStopDTO(String code, String name, String zone, Point2D.Double coordinates) {
        this.code = code;
        this.name = name;
        this.zone = zone;
        this.coordinates = coordinates;
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

    public Point2D.Double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D.Double coordinates) {
        this.coordinates = coordinates;
    }
}
