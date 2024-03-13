package com.stcp_api.domain.model;

import jakarta.persistence.*;

import java.awt.geom.Point2D;
import java.util.List;

@Entity
public class BusStop {

    @Id
    @SequenceGenerator(name = "bus_stop_sequence", sequenceName = "bus_stop_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String code;
    private String name;
    private String zone;

    @Transient
    private Point2D location;
    @ManyToMany
    private List<BusLine> lines;

    protected BusStop() {

    }

    public BusStop(String code, String name, String zone, Point2D location, List<BusLine> lines) {
        this.code = code;
        this.name = name;
        this.zone = zone;
        this.location = location;
        this.lines = lines;
    }

    public BusStop(int id, String code, String name, String zone, Point2D location, List<BusLine> lines) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.zone = zone;
        this.location = location;
        this.lines = lines;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<BusLine> getLines() {
        return lines;
    }

    public void setLines(List<BusLine> lines) {
        this.lines = lines;
    }
}
