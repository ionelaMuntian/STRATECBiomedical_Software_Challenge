package com.example.stratec.Model;

public class Planet {
    private String name; // Name of the planet
    private Double diameter; // Diameter in meters
    private Double mass; // Mass in kilograms
    private Integer orbitalPeriod; // Orbital period in days
    private Double orbitalRadius; // Orbital radius in meters
    private Double escapeVelocity; // Escape velocity in meters per second (m/s)
    private Double timeEscape; // Time to escape in seconds
    private Double distanceEscape; // Distance to escape in meters

    public Planet(String name, Double diameter, Double mass) {
        this.name = name;
        this.diameter = diameter;
        this.mass = mass;
        this.orbitalPeriod = null;
        this.orbitalRadius = null;
        this.escapeVelocity = null;
        this.timeEscape = null;
        this.distanceEscape = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Integer getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(Integer orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public Double getOrbitalRadius() {
        return orbitalRadius;
    }

    public void setOrbitalRadius(Double orbitalRadius) {
        this.orbitalRadius = orbitalRadius;
    }

    public Double getEscapeVelocity() {
        return escapeVelocity;
    }

    public void setEscapeVelocity(Double escapeVelocity) {
        this.escapeVelocity = escapeVelocity;
    }

    public Double getTimeEscape() {
        return timeEscape;
    }

    public void setTimeEscape(Double timeEscape) {
        this.timeEscape = timeEscape;
    }

    public Double getDistanceEscape() {
        return distanceEscape;
    }

    public void setDistanceEscape(Double distanceEscape) {
        this.distanceEscape = distanceEscape;
    }
}
